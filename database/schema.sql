USE concessionaria_db;

-- Corrige a coluna chassi para aceitar qualquer tamanho e não ser unique obrigatório
ALTER TABLE veiculos MODIFY chassi VARCHAR(50);
ALTER TABLE veiculos MODIFY placa VARCHAR(20);

SELECT 'Atualização concluída!' as status;
