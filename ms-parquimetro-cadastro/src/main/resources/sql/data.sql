
-- Inserindo dados na tabela `clientes`
INSERT INTO public.clientes (id, nome, cnh, telefone, email, forma_pagamento_preferida) VALUES
                                                                                     ('550e8400-e29b-41d4-a716-446655440000', 'João Silva', '12345678901', '11987654321', 'joao.silva@example.com', 'CREDITO'),
                                                                                     ('550e8400-e29b-41d4-a716-446655440001', 'Maria Oliveira', '10987654321', '21987654321', 'maria.oliveira@example.com', 'DEBITO'),
                                                                                     ('550e8400-e29b-41d4-a716-446655440002', 'Carlos Pereira', '22345678901', '31987654321', 'carlos.pereira@example.com', 'PIX'),
                                                                                     ('550e8400-e29b-41d4-a716-446655440003', 'Ana Souza', '32345678901', '41987654321', 'ana.souza@example.com', 'DINHEIRO'),
                                                                                     ('550e8400-e29b-41d4-a716-446655440004', 'Luiz Santos', '42345678901', '51987654321', 'luiz.santos@example.com', 'CREDITO');
commit;
-- Inserindo dados na tabela `carro`
INSERT INTO public.carro (id, marca, modelo, cor, placa, observacoes, cliente_id) VALUES
                                                                               ('550e8400-e29b-41d4-a716-446655440005', 'Ford', 'Fiesta', 'Vermelho', 'ABC1234', 'Sem observações', '550e8400-e29b-41d4-a716-446655440000'),
                                                                               ('550e8400-e29b-41d4-a716-446655440006', 'Chevrolet', 'Onix', 'Preto', 'XYZ5678', 'Com insulfilm', '550e8400-e29b-41d4-a716-446655440001'),
                                                                               ('550e8400-e29b-41d4-a716-446655440007', 'Toyota', 'Corolla', 'Branco', 'EFG9012', 'Revisão em dia', '550e8400-e29b-41d4-a716-446655440002'),
                                                                               ('550e8400-e29b-41d4-a716-446655440008', 'Volkswagen', 'Golf', 'Azul', 'HIJ3456', 'Carro novo', '550e8400-e29b-41d4-a716-446655440003'),
                                                                               ('550e8400-e29b-41d4-a716-446655440009', 'Hyundai', 'HB20', 'Cinza', 'KLM7890', 'Com GPS', '550e8400-e29b-41d4-a716-446655440004');
commit;