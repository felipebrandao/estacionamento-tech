db = db.getSiblingDB('usuario');

db.usuario.insertMany([
    {
        _id: UUID("1a417508-a5ee-4c8b-88e0-e6049e60bb72"),
        nome: "João Silva",
        email: "joao.silva@example.com",
        cpf: "12345678901",
        tipoUsuarioEnum: "COMUM",
        senha: "123456"
    },
    {
        _id: UUID("1c0e9e4d-74a3-4623-b373-3bf1b85a8962"),
        nome: "Maria Oliveira",
        email: "maria.oliveira@example.com",
        cpf: "98765432109",
        tipoUsuarioEnum: "FISCAL",
        senha: "123456"
    }
]);
