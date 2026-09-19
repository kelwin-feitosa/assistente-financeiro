package com.kelwin.assistente_financeiro.application.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!test")
@Service
public class WhisperService {

    private final String whisperCliPath;
    private final String whisperModelPath;

    public WhisperService(
            @Value("${whisper.cli.path}") String whisperCliPath,
            @Value("${whisper.model.path}") String whisperModelPath) {
        this.whisperCliPath = whisperCliPath;
        this.whisperModelPath = whisperModelPath;
    }

    public String transcrever(Path audio) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(
                whisperCliPath,
                "-m", whisperModelPath,
                "-f", audio.toString(),
                "-l", "pt",
                "--no-timestamps"
        )
                .redirectErrorStream(true)
                .start();

        String resultado = new String(
                process.getInputStream().readAllBytes()
        );

        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new IllegalStateException(
                    "Falha ao executar o Whisper: " + resultado
            );
        }

        return extrairTranscricao(resultado);
    }

    private String extrairTranscricao(String resultado) {
        return resultado.lines()
                .filter(linha -> !linha.isBlank())
                .filter(linha -> !linha.startsWith("whisper_"))
                .filter(linha -> !linha.startsWith("system_info"))
                .filter(linha -> !linha.startsWith("main:"))
                .filter(linha -> !linha.startsWith("[")) // remove timestamps
                .reduce((primeira, segunda) -> primeira + " " + segunda)
                .orElse("");
    }
}