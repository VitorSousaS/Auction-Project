INSERT INTO unidade (
	id,
	numeroLote,
	nome,
	createdAt,
	updatedAt
) VALUES 
((SELECT nextval('seq_unidade')),73908, 'Unidade de Pesquisa Histórica','2019-01-02','2019-02-02'),
((SELECT nextval('seq_unidade')),83570, 'Centoro de Coleta de Metais','2019-01-04','2019-01-05'),
((SELECT nextval('seq_unidade')),14582, 'Centro de Análises de Valiosos','2019-01-06','2019-01-10'),
((SELECT nextval('seq_unidade')),26585, 'Unidade de Pesquisa Histórica','2019-01-06','2019-05-20'),
((SELECT nextval('seq_unidade')),47052, 'Pesquisa e Reestruturação de Artefatos','2019-01-25','2019-04-20'),
((SELECT nextval('seq_unidade')),85423, 'Mineradora São Critovão','2019-02-06','2020-05-24'),
((SELECT nextval('seq_unidade')),94672, 'Centro Artístico de São Paulo','2019-01-02','2023-06-25'),
((SELECT nextval('seq_unidade')),46934, 'Garleria da Arte Moderna','2019-03-16','2022-08-018'),
((SELECT nextval('seq_unidade')),14693, 'Museu Central do Brasil','2019-06-12','2021-09-21'),
((SELECT nextval('seq_unidade')),16952, 'Casa da História','2019-10-19','2019-10-20'),
((SELECT nextval('seq_unidade')),30174, 'Unidade de Reconhecimento Artístico','2019-12-15','2023-11-17'),
((SELECT nextval('seq_unidade')),73342, 'Centro Acadêmico Belas Artes','2021-02-15','2022-05-19'),
((SELECT nextval('seq_unidade')),16784, 'Antônio Ourives','2023-06-12','2023-08-21'),
((SELECT nextval('seq_unidade')),73452, 'Siqueira Topeira','2022-03-14','2022-07-23'),
((SELECT nextval('seq_unidade')),92484, 'Unidade Artes Mirim','2020-02-02','2020-07-20');