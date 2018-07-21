package protocolsupport.api.remapper;

import org.apache.commons.lang3.Validate;
import org.bukkit.Effect;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.utils.RemappingTable.HashMapBasedIdRemappingTable;

/**
 * @deprecated Isn't used, was added by mistake, and isn't useful anyway because it doesn't support remapping data along with effect id
 */
@Deprecated
public class EffectRemapperControl {

	private final HashMapBasedIdRemappingTable table;

	public EffectRemapperControl(ProtocolVersion version) {
		Validate.isTrue(version.isSupported(), "Can't control effect remapping for unsupported version");
		table = IdRemapper.EFFECT.getTable(version);
	}

	public void setRemap(Effect from, Effect to) {
		if (from.getType() != to.getType()) {
			throw new IllegalArgumentException("Effect types differ");
		}
		setRemap(from.getId(), to.getId());
	}

	public void setRemap(int from, int to) {
		table.setRemap(from, to);
	}

	public Effect getRemap(Effect from) {
		return Effect.getById(getRemap(from.getId()));
	}

	public int getRemap(int from) {
		return table.getRemap(from);
	}

}
