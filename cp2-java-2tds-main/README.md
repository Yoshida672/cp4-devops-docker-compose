http://localhost:8080/swagger-ui/index.html

Possíveis moves

{
    "name": "Tackle",
    "description": "Um ataque físico básico em que Bulbasaur investe contra o oponente.",
    "type": "NORMAL",
    "category": "PHYSICAL",
    "power": 40.0,
    "accuracy": 100.0,
    "ppMax": 35
}

Pokemon

{
    "number":1,
    "name":"Bulbasaur",
    "title":"Pokémon Sapo",
    "description": "Um tempo depois do seu nascimento, ele usa os nutrientes contidos na semente em suas costas para crescer",
    "possibleGender":["MALE", "FEMALE"],
    "possibleAbility":["Overgrow", "Chlorophyll"],
    "typePrimary": "GRASS",
    "typeSecondary": "POISON",
    "lineEvolution": 1,
    "possibleMovesLearnId":[1]
}

Trainer

{
    "name":"Eric",
    "startJourney":"2025-04-11",
    "money": 69.0,
    "pokemonHasCaught":1,
    "pokemonHasView": 5,
    "numberBadge":0,
    "city": "Kanto"        
}

Pokemon do Trainer

{
    "nickname": "Sapão",
    "groupExp" : "MEDIUM_SLOW",
    "pokemonId" : 1,
    "gender" : "MALE",
    "ability" : "Overgrow",
    "nature" : "GENTLE",
    "status" : {
        "hpBase": 45,
        "attackBase": 49,
        "spAttackBase": 49,
        "defenseBase": 65,
        "spDefenseBase": 65,
        "speedBase": 45
    },
    "location" : "Kanto",
    "trainerId" : 1,
    "dateCapture" : "2025-04-11",
    "movesId" : [1]
}
