# Conclusão

Termina-se assim o manual relativo à Calculadora de Tempos Universal.
Embora o trabalho esteja bastante completo, consideramos que existem pontos que poderiam ser melhorados.
Entre eles estariam a implementação dos seguintes métodos:

 - `Pagamentos` - Módulo responsável por pagamentos periódicos, sendo exemplos do mesmo a anuidade de um seguro automóvel, propinas de alunos, etc. A implementação do mesmo seria relativamente fácil, sendo apenas necessário perguntar ao utilizador a periocidade do pagamento, bem como a quantia do mesmo e a data da 1º prestação. Seria também útil exportar esta informação para um ficheiro de texto para futura leitura.

 - `Cronómetro` - O módulo Cronómetro apenas permite iniciar, pausar/continuar e paragem do mesmo. Seria interessante a implementação de um sistema responsável por guardar o ponto atual do cronómetro para futuro carregamento no sistema. Além disso poderiamos criar um sistema responsável por guardar o *runtime* do cronómetro atual quando fosse pressionada uma tecla, de modo a guardar vários *timestamps*.

- `Calendário` - O módulo Calendário apenas permite mostrar o calendário de um dado mês e ano em *pretty-printing*, sendo que uma possivel adição a nível de funcionalidades seria a sincronização com o módulo responsável pelos Appointments e Travels. Assim sendo, no *pretty-printing* do calendário seria adicionada informação relativa aquele mês, como por exemplo: viagens/appointments que ocorrem naquele mês, etc.

A implementação das funcionalidades em cima descritas seria trivial, visto que o projeto foi desenvolvido de forma a ter uma fácil expansão de modos de funcionamento.
Relativamente ao Java 8, podemos afirmar que se tratou de uma agradável surpresa, sendo que a programação baseada no paradigma funcional simplificou a escrita de código. Além disso, a utilização de *Streams*, além de oferecer um ganho de performance, simplifica muito a escrita de código comparativamente com versões anteriores do Java.
