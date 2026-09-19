package com.kelwin.assistente_financeiro.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PiperService {

    private final String piperPath;
    private final String modelPath;

    public PiperService(
            @Value("${piper.cli.path}") String piperPath,
            @Value("${piper.model.path}") String modelPath) {
        this.piperPath = piperPath;
        this.modelPath = modelPath;
    }

    public Path sintetizar(String texto) throws IOException, InterruptedException {
        Path arquivoSaida = Files.createTempFile("assistente-resposta-", ".wav");

        Process process = new ProcessBuilder(
                piperPath,
                "-m", modelPath,
                "-f", arquivoSaida.toString()
        )
                .redirectError(ProcessBuilder.Redirect.DISCARD)
                .start();

        process.getOutputStream().write(texto.getBytes());
        process.getOutputStream().close();

        int exitCode = process.waitFor();

        if (exitCode != 0) {
            Files.deleteIfExists(arquivoSaida);
            throw new IllegalStateException("Falha ao executar o Piper.");
        }

        return arquivoSaida;
    }
}