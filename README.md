
# exercicio_primeiro_selenium


DOCUMENTAÇÃO DE TESTES DE SOFTWARE: PCE + AVL
DISCIPLINA / PROJETO: AUTOMAÇÃO DE TESTES E2E COM SELENIUM E JUNIT 5
SITE ALVO: http://automationexercise.com


--------------------------------------------------------------------------------
PARTE 1: CASO DE USO 3 - Login User with incorrect email and password
--------------------------------------------------------------------------------

1. O QUE É:
   O Particionamento em Classes de Equivalência (PCE) e a Análise de Valor Limite
   (AVL) foram aplicados para validar o formulário de login sob condições adversas.
   O objetivo é garantir que qualquer combinação de dados incorretos ou em limites
   de formatação resulte na exibição da mensagem de erro esperada:
   "Your email or password is incorrect!".

2. COMO FOI FEITO:
   Dividiu-se o domínio de entrada dos campos "Email" e "Password" em classes válidas
   e inválidas, avaliando as bordas dos tamanhos de string e formatos sintáticos.

   - Classes de Equivalência (PCE):
     * CEI_1 (Email): String sem o caractere '@' (Sintaticamente inválida).
     * CEI_2 (Email): Email válido formato padrão, porém não cadastrado no banco.
     * CEI_3 (Senha): Campo de senha em branco (Vazio).
     * CEI_4 (Senha): Senha válida em formato, porém divergente do e-mail.

   - Análise de Valor Limite (AVL):
     * AVL_1 (Senha): Comprimento mínimo de borda (1 caractere).
     * AVL_2 (Email): String no limite superior de tamanho (Email extenso com > 100 chars).

   MATRIZ DAS 5 ENTRADAS DE TESTE (EXECUTADAS VIA @ParameterizedTest):
   -----------------------------------------------------------------------------
   | Entrada | Email Inserido                           | Senha Inserida    | Critério Aplicado |
   |---------|------------------------------------------|-------------------|-------------------|
   | 1       | usuario_invalido_sem_arroba.com         | Senha123456       | PCE (Email Inv.)  |
   | 2       | nao_cadastrado_avl1@teste.com            | a                 | AVL (Senha Mín)   |
   | 3       | usuario_com_email_extremamente_longo... | SenhaPadrão123    | AVL (Email Máx)   |
   | 4       | email_valido_nao_existente@dominio.com   | [Vazio]           | PCE (Senha Vazia) |
   | 5       | usuario_desconhecido_2026@gmail.com      | SenhaErrada999    | PCE (Cred. Inexist)|
   -----------------------------------------------------------------------------

3. POR QUE FOI FEITO ASSIM:
   Testar apenas um e-mail e senha aleatórios não garante que o sistema lida de
   forma robusta com falhas no front-end ou validações de banco de dados.
   A combinação de PCE + AVL reduz o número de testes necessários enquanto mantém
   uma alta cobertura contra falhas de injeção de dados e exceções não tratadas.


--------------------------------------------------------------------------------
PARTE 2: CASO DE USO 1 - Register User (Fluxo Completo)
--------------------------------------------------------------------------------

1. O QUE É:
   Aplica PCE e AVL sobre os campos de entrada do formulário de cadastro de usuário
   (Nome, Senha, Telefone) ao longo do fluxo de cadastro, verificação de sessão e
   exclusão da conta recém-criada (E2E).

2. COMO FOI FEITO:
   Para que o teste E2E funcione perfeitamente de ponta a ponta sem falhar na criação
   da conta, foi mantido o e-mail único gerado por timestamp (evitando duplicidade)
   e variaram-se as entradas dos demais campos obrigatórios com base em limites e classes.

   - Classes de Equivalência (PCE):
     * CEV_1 (Nome/Telefone): Formato e tamanho padrão do cotidiano.
     * CEV_2 (Telefone): Formato com DDI (ex: 5521955555555).

   - Análise de Valor Limite (AVL):
     * AVL_1 (Nome): Comprimento mínimo aceitável no limite inferior (2 caracteres).
     * AVL_2 (Nome): Comprimento no limite superior (Nome completo extenso).
     * AVL_3 (Senha): Senha no limite mínimo permitido pelo formulário (6 caracteres).

   MATRIZ DAS 5 ENTRADAS DE TESTE (EXECUTADAS VIA @ParameterizedTest):
   -----------------------------------------------------------------------------
   | Entrada | Nome do Usuário                          | Senha             | Telefone          | Critério Aplicado |
   |---------|------------------------------------------|-------------------|-------------------|-------------------|
   | 1       | Estagiario Alfa                          | SenhaFortissima123| 21999999999       | PCE (Padrão)      |
   | 2       | Jo                                       | SenhaMinima12     | 11988888888       | AVL (Nome Mínimo) |
   | 3       | Alexandre de Alcantara e Silva Santos Jr | SenhaEspecial!@#  | 31977777777       | AVL (Nome Extenso)|
   | 4       | Dev Tester                               | 123456            | 41966666666       | AVL (Senha Mín 6) |
   | 5       | Maria Oliveira                           | MinhaSenha2026    | 5521955555555     | PCE (Tel com DDI) |
   -----------------------------------------------------------------------------

3. POR QUE FOI FEITO ASSIM:
   O processo de registro de usuário em e-commerce possui múltiplos campos de texto
   sujeitos a erros de validação de tamanho. Testar dados limítrofes (nomes curtos,
   nomes longos e senhas no tamanho limite) garante que o banco de dados e as telas de
   confirmação ("ACCOUNT CREATED!" e "Logged in as") processam e exibem corretamente
   as informações gravadas antes de deletar a conta ao final da execução.

# Repositório com os exemplos usados na aula sobre Selenium

Sequência das branches

1. aula_google
2. testa_busca
3. testa_usuario
4. testa_usuario_po

