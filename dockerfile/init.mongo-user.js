db = db.getSiblingDB('parking');

db.users.insertMany([
    {
        _id: new BinData(3, "7ERiOO0pyb8blPwUezF2vg=="),
        cpf: "86654708083",
        email: "joao.silva@example.com",
        name: "João Silva",
        password: "$2a$10$X2JvdHuhDVD02ITlbP3wOuNsM2PLSdELGHNaSCHfheLhMT14gcQyu",
        userType: "COMUM",
        _class: "com.fiap.techchallenge.user.model.User"
    },
    {
        _id: new BinData(3, "qkTkvNiqowsDT3ieDciVvQ=="),
        cpf: "13893266089",
        email: "maria.oliveira@example.com",
        name: "Maria Oliveira",
        password: "$2a$10$dNQy.m7UmyAcXabxWHpgAuUI6eGH1WdPbMxpkHHB1DQ8D.gKu3RNi",
        userType: "FISCAL",
        _class: "com.fiap.techchallenge.user.model.User"
    }
]);
