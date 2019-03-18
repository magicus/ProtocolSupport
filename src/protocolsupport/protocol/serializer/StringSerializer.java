package protocolsupport.protocol.serializer;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import io.netty.buffer.ByteBuf;
import protocolsupport.api.ProtocolVersion;

public class StringSerializer {

	public static String readVarIntUTF8String(ByteBuf from) {
		return new String(MiscSerializer.readBytes(from,  VarNumberSerializer.readVarInt(from)), StandardCharsets.UTF_8);
	}

	public static String readString(ByteBuf from, ProtocolVersion version) {
		return readString(from, version, Short.MAX_VALUE);
	}

	public static String readString(ByteBuf from, ProtocolVersion version, int limit) {
		if (isUsingUTF16(version)) {
			int length = from.readUnsignedShort() * 2;
			MiscSerializer.checkLimit(length, limit * 4);
			return new String(MiscSerializer.readBytes(from, length), StandardCharsets.UTF_16BE);
		} else if (isUsingUTF8(version)) {
			int length = VarNumberSerializer.readVarInt(from);
			MiscSerializer.checkLimit(length, limit * 4);
			return new String(MiscSerializer.readBytes(from, length), StandardCharsets.UTF_8);
		} else {
			throw new IllegalArgumentException(MessageFormat.format("Dont know how to read string of version {0}", version));
		}
	}

	public static void writeVarIntUTF8String(ByteBuf to, String string) {
		byte[] data = string.getBytes(StandardCharsets.UTF_8);
		VarNumberSerializer.writeVarInt(to, data.length);
		to.writeBytes(data);
	}

	public static void writeString(ByteBuf to, ProtocolVersion version, String string) {
		if (isUsingUTF16(version)) {
			to.writeShort(string.length());
			to.writeBytes(string.getBytes(StandardCharsets.UTF_16BE));
		} else if (isUsingUTF8(version)) {
			byte[] data = string.getBytes(StandardCharsets.UTF_8);
			VarNumberSerializer.writeVarInt(to, data.length);
			to.writeBytes(data);
		} else {
			throw new IllegalArgumentException(MessageFormat.format("Dont know how to write string of version {0}", version));
		}
	}

	private static boolean isUsingUTF16(ProtocolVersion version) {
		return version.isPC() && version.isBeforeOrEq(ProtocolVersion.MINECRAFT_1_6_4);
	}

	private static boolean isUsingUTF8(ProtocolVersion version) {
		return
			(version.isPC() && version.isAfterOrEq(ProtocolVersion.MINECRAFT_1_7_5)) ||
			(version.isPE());
	}

}
