# Tetris

Esqueleto em Java 21 e JavaFX, com estrutura MVC e pontos de integração para
Singleton, Facade, State e Command.

## Executar

Abra ou recarregue o projeto Maven no IntelliJ e execute `clean` e `compile`
em **Lifecycle**; depois execute `javafx:run` em **Plugins → javafx**.

No terminal:

```bash
./mvnw clean compile javafx:run
```

Requer JDK 21 e uma sessão gráfica acessível pelo processo JavaFX.

## Controles

- `←` / `→`: mover a peça;
- `↓`: descer uma linha;
- `↑`: girar;
- `Espaço`: soltar até o fundo;
- `P`: pausar/continuar;
- `R`: reiniciar.

O esboço inicia com uma peça T. Ainda não implementa queda automática,
fixação das peças, remoção de linhas, pontuação nem Game Over.
