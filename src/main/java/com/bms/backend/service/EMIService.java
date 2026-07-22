package com.bms.backend.service;

import java.util.UUID;

import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;

public interface EMIService {
	EMIResponse createEMI(EMIRequestdto request);
	EMIResponse getEMIById(UUID emiId);

}
