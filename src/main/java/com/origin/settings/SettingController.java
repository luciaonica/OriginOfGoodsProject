package com.origin.settings;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.origin.FileUploadUtil;
import com.origin.entity.Currency;
import com.origin.entity.Setting;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SettingController {
	
	@Autowired private SettingService service;
	
	@Autowired private CurrencyRepository currencyRepo;
	
	@GetMapping("/settings")
	public String listAll(Model model) {	
		List<Setting> listSettings = service.listAllSettings();
		List<Currency> listCurrencies = currencyRepo.findAllByOrderByNameAsc();
		
		model.addAttribute("listCurrencies", listCurrencies);
		
		for(Setting setting : listSettings) {
			model.addAttribute(setting.getKey(), setting.getValue());
		}
		
		return "settings/settings";
	}
	
	@PostMapping("/settings/save_general")
	public String saveGeneralSettings(@RequestParam("fileImage") MultipartFile multipartFile, 
			HttpServletRequest request, RedirectAttributes ra) throws IOException {		
				
		List<Setting> settings = service.listAllSettings();
		
		saveSiteLogo(multipartFile, settings);
				
		updateSettingValuesFromForm(request, settings);
		
		ra.addFlashAttribute("message", "General settings have been saved.");		
		
		return "redirect:/settings";		
	}
	
	private void saveSiteLogo(MultipartFile multipartFile, List<Setting> settings) throws IOException {
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String value = "/site-logo/" + fileName;
			service.updateSiteLogo("SITE_LOGO", value);
			
			String uploadDir = "../site-logo/";				
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);	
		}
	}
	
	
	
	private void updateSettingValuesFromForm(HttpServletRequest request, List<Setting> listSettings) {
		for(Setting setting : listSettings) {
			String value = request.getParameter(setting.getKey());
			if (value != null) {
				setting.setValue(value);
			}
		}
		
		service.saveAll(listSettings);
	}
}
