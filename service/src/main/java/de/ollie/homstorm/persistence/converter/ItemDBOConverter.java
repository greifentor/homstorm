package de.ollie.homstorm.persistence.converter;

import org.springframework.stereotype.Component;

import de.ollie.homstorm.persistence.dbo.ItemDBO;
import de.ollie.homstorm.service.so.ItemSO;

/**
 * A converter for item DBO's.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Component
public class ItemDBOConverter {

	public ItemSO convertDBOToSO(ItemDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new ItemSO() //
				.setId(dbo.getId()) //
				.setDescription(dbo.getDescription()) //
				.setMeals(dbo.getMeals()) //
				.setMessageDaysBeforeBestBeforeDate(dbo.getMessageDaysBeforeBestBeforeDate());
	}

	public ItemDBO convertSOToDBO(ItemSO so) {
		if (so == null) {
			return null;
		}
		return new ItemDBO() //
				.setId(so.getId()) //
				.setDescription(so.getDescription()) //
				.setMeals(so.getMeals()) //
				.setMessageDaysBeforeBestBeforeDate(so.getMessageDaysBeforeBestBeforeDate());
	}

}