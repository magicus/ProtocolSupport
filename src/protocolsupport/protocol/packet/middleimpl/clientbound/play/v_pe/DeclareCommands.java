package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_pe;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleDeclareCommands;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class DeclareCommands extends MiddleDeclareCommands {

	public DeclareCommands(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public void readFromServerData(ByteBuf serverdata) {
		super.readFromServerData(serverdata);
		System.out.println("READ DECLARE FROM SERVER");
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	@Override
	public RecyclableCollection<ClientBoundPacketData> toData() {
		System.out.println("GETTING DECLARE COMMANDS");
		byte[] bytes = new byte[19853];
		data.getBytes(0, bytes);
		System.out.println(bytes);
		System.out.println("as hex:");
		System.out.println(bytesToHex(bytes));
		return RecyclableEmptyList.get();
	}

}
