package com.origin.settings;

import org.springframework.data.jpa.repository.JpaRepository;
import com.origin.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting, Integer>{

}
