package de.ollie.homstorm.persistence.converter;

import org.springframework.stereotype.Component;

import de.ollie.homstorm.persistence.dbo.StoragePlaceDBO;
import de.ollie.homstorm.service.so.StoragePlaceSO;

/**
 * A converter for storageplace DBO's.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Component
public class StoragePlaceDBOConverter {

	public StoragePlaceSO convertDBOToSO(StoragePlaceDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new StoragePlaceSO().setId(dbo.getId()).setDescription(dbo.getDescription());
	}

	public StoragePlaceDBO convertSOToDBO(StoragePlaceSO so) {
		if (so == null) {
			return null;
		}
		return new StoragePlaceDBO().setId(so.getId()).setDescription(so.getDescription());
	}

}