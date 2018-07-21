package protocolsupport.api.events;

import java.net.InetSocketAddress;

import protocolsupport.api.Connection;
import protocolsupport.api.ProtocolSupportAPI;

/**
 * @deprecated This class isn't useful anymore
 */
@Deprecated
public abstract class PlayerEvent extends ConnectionEvent {

	private final String username;

	public PlayerEvent(Connection connection, String username, boolean async) {
		super(connection, async);
		this.username = username;
	}

	public PlayerEvent(Connection connection, String username) {
		this(connection, username, true);
	}

	public PlayerEvent(InetSocketAddress address, String username) {
		this(ProtocolSupportAPI.getConnection(address), username);
	}

	/**
	 * Returns the player original nickname <br>
	 * Returns {@link Profile#getOriginalName()}
	 * @return player nickname
	 */
	public String getName() {
		return username;
	}

}
