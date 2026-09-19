package com.kelwin.assistente_financeiro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.kelwin.assistente_financeiro.application.service.PiperService;
import com.kelwin.assistente_financeiro.application.service.WhisperService;

@SpringBootTest
@ActiveProfiles("test")
class AssistenteFinanceiroApplicationTests {

	@MockitoBean
    private WhisperService whisperService;

    @MockitoBean
    private PiperService piperService;
	
	@Test
	void contextLoads() {
	}

}
