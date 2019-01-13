package protocolsupport.utils;

import protocolsupport.ProtocolSupport;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_pe.EntityHeadRotation;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_pe.EntityTeleport;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_pe.EntityVelocity;

import java.text.MessageFormat;

public class DebugUtils {
	// Set to true to enable detailed packet debugging
	private boolean packetDebugging = true;

	private static DebugUtils instance;

	public static DebugUtils getInstance() {
		if (instance == null) {
			instance = new DebugUtils();
		}
		return instance;
	}

	public boolean isPacketDebugging() {
		return packetDebugging;
	}

	public void logSentPacket(ClientBoundMiddlePacket packetTransformer) {
		if (isPacketDebugging()) {
			if (packetTransformer.getClass() == EntityTeleport.class ||
				packetTransformer.getClass() == EntityHeadRotation.class ||
				packetTransformer.getClass() == EntityVelocity.class) {
				// ignore TimeUpdate Noop
			} else {
				ProtocolSupport.logInfo(MessageFormat.format("PE_SEND: {0}", packetTransformer));
			}
		}
	}

	public void logReadPacket(ServerBoundMiddlePacket packetTransformer) {
		if (isPacketDebugging()) {
			ProtocolSupport.logInfo(MessageFormat.format("PE_RECV: {0}", packetTransformer));
		}
	}

}
