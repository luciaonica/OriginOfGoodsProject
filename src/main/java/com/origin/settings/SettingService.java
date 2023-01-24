package com.origin.settings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.entity.Setting;

@Service
public class SettingService {
	@Autowired private SettingRepository repo;
	
	public List<Setting> listAllSettings(){
		return (List<Setting>) repo.findAll();		
	}
	
	public void saveAll(Iterable<Setting> settings) {
		repo.saveAll(settings);
	}
	
	public Setting get(String key) {
		List<Setting> listSettings = (List<Setting>) repo.findAll();
		int index = listSettings.indexOf(new Setting(key));
		if (index >= 0) {
			return listSettings.get(index);
		}
		
		return null;
	}

	public void updateSiteLogo(String key, String value) {
		Setting setting = get(key);
		if (setting != null && value != null) {
			setting.setValue(value);;
		}			
	}
	
	public List<Setting> getGeneralSettings() {		
		return (List<Setting>) repo.findAll();		
	}
}
