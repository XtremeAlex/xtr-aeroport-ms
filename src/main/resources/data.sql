
INSERT INTO volo (id, numero_volo, destinazione, orario_partenza, tipo_aereo)
VALUES (1, 'V123', 'Roma', '2023-01-01T10:00:00', 'Boeing 737');
INSERT INTO volo (id, numero_volo, destinazione, orario_partenza, tipo_aereo)
VALUES (2, 'V132', 'Firenze', '2023-01-02T14:45:00', 'Airbus A310');
INSERT INTO volo (id, numero_volo, destinazione, orario_partenza, tipo_aereo)
VALUES (3, 'V131', 'Genova', '2099-01-02T12:15:00', 'Boeing 767');
INSERT INTO volo (id, numero_volo, destinazione, orario_partenza, tipo_aereo)
VALUES (4, 'V130', 'Catania', '2023-01-02T10:30:00', 'Airbus A380');
INSERT INTO volo (id, numero_volo, destinazione, orario_partenza, tipo_aereo)
VALUES (5, 'V129', 'Palermo', '2023-01-02T08:00:00', 'Boeing 787');

/* A_T_AIRPORT_TYPE */
INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('balloonport');
INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('heliport');
INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('large_airport');
INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('medium_airport');
INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('seaplane_base');
/*INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('small_airport');  -> TOLTO PER TEST*/
INSERT INTO A_T_AIRPORT_TYPE (name) VALUES ('not_set');

