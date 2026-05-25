O objetivo do projeto foi praticar lógica de programação, manipulação gráfica, orientação a objetos e funcionamento de jogos em tempo real utilizando Java Swing.


O jogo possui:
• Sistema de gravidade
• Movimentação do personagem via teclado
• Geração dinâmica de obstáculos
• Sistema de colisão
• Contador de pontos
• Reinício da partida após Game Over
• Loop contínuo de renderização e atualização do jogo
Durante o desenvolvimento, consegui aprofundar conhecimentos em:


Estruturação de aplicações desktop


Manipulação de eventos


Lógica de jogos 2D


Atualização gráfica em tempo real


Organização de código orientado a objetos

---

## Publicar na Vercel (versão web)

O erro **404 NOT_FOUND** aparece quando não existe nenhum arquivo para a raiz (`/`), por exemplo sem `index.html`. Este repositório inclui **`index.html`**, um jogo jogável no navegador (Canvas + JavaScript). O **Java Swing** em `src/` continua igual para uso local.

1. Faça commit e envie ao GitHub o `index.html` na raiz do repositório.
2. Na Vercel (**Project Settings → General**): **Framework Preset: Other**; sem **Build Command**; **Output Directory** vazio ou `.`.
3. Se no GitHub seu projeto estiver dentro de uma subpasta, em **Root Directory** aponte para essa pasta; se `index.html` já estiver na raiz do repositório ligado ao GitHub, não altere isso.
4. Faça **Redeploy** do projeto.
