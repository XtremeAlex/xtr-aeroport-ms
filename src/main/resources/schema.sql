CREATE TABLE IF NOT EXISTS Volo
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_volo     VARCHAR(255),
    destinazione    VARCHAR(255),
    orario_partenza TIMESTAMP,
    tipo_aereo      VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS  A_T_AIRPORT_TYPE (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(255),
                                  description VARCHAR(255),
                                  created_date TIMESTAMP,
                                  last_modified_date TIMESTAMP,
                                  created_by VARCHAR(255),
                                  last_modified_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS  A_D_AIRPORT (
                          id INT AUTO_INCREMENT NOT NULL,
                          continent VARCHAR(255) ,
                          coordinates VARCHAR(255) ,
                          elevation_ft INT ,
                          gps_code VARCHAR(255) ,
                          iata_code VARCHAR(255),
                          ident VARCHAR(255) ,
                          iso_country VARCHAR(255),
                          iso_region VARCHAR(255),
                          local_code VARCHAR(255),
                          municipality VARCHAR(255) ,
                          name VARCHAR(255) NOT NULL,
                          id_airport_type INT,
                          PRIMARY KEY (id)
);

COMMENT ON COLUMN A_D_AIRPORT.id IS 'ID dell aeroporto, un codice alfanumerico univoco utilizzato dalle autorità aeronautiche locali.';
COMMENT ON COLUMN A_D_AIRPORT.continent IS 'Continente in cui si trova l aeroporto.';
COMMENT ON COLUMN A_D_AIRPORT.coordinates IS 'Coordinate geografiche dell aeroporto, espresse in longitudine e latitudine.';
COMMENT ON COLUMN A_D_AIRPORT.elevation_ft IS 'Altezza dell aeroporto in piedi sopra il livello del mare.';
COMMENT ON COLUMN A_D_AIRPORT.gps_code IS 'Codice GPS dell aeroporto, un codice alfanumerico univoco assegnato dall ICAO (Organizzazione Internazionale dell Aviazione Civile).';
COMMENT ON COLUMN A_D_AIRPORT.iata_code IS 'Codice IATA dell aeroporto, un codice alfanumerico di tre lettere utilizzato dalle compagnie aeree.';
COMMENT ON COLUMN A_D_AIRPORT.ident IS 'ID dell aeroporto, un codice alfanumerico univoco utilizzato dalle autorità aeronautiche locali.';
COMMENT ON COLUMN A_D_AIRPORT.iso_country IS 'Paese in cui si trova l aeroporto.';
COMMENT ON COLUMN A_D_AIRPORT.iso_region IS 'Codice ISO della regione in cui si trova l aeroporto.';
COMMENT ON COLUMN A_D_AIRPORT.local_code IS 'Codice locale dell aeroporto, un codice alfanumerico usato dalle autorità aeronautiche locali per identificare l aeroporto in modo specifico.';
COMMENT ON COLUMN A_D_AIRPORT.municipality IS 'Comune in cui si trova l aeroporto.';
COMMENT ON COLUMN A_D_AIRPORT.name IS 'Nome dell aeroporto.';
COMMENT ON COLUMN A_D_AIRPORT.id_airport_type IS 'Tipo di aeroporto.';
//COMMENT ON COLUMN AIRPORT.createdUser IS 'Utente che ha creato o modificato l aeroporto.';
//COMMENT ON COLUMN AIRPORT.createdDate IS 'Data e ora di creazione dell aeroporto.';
//COMMENT ON COLUMN AIRPORT.updatedDate IS 'Data e ora dell ultima modifica dell aeroporto.';


CREATE TABLE IF NOT EXISTS  A_T_COUNTRY (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 name VARCHAR(255),
                                                 iso_country VARCHAR(10),
                                                 description VARCHAR(255),
                                                 created_date TIMESTAMP,
                                                 last_modified_date TIMESTAMP,
                                                 created_by VARCHAR(255),
                                                 last_modified_by VARCHAR(255)
);


CREATE OR REPLACE VIEW V_D_COUNTRY_AIRPORT_COUNT AS (
SELECT
    c.iso_Country,
    c.name,
    COUNT(a.id) AS airport_count
FROM
    A_T_COUNTRY c
        LEFT JOIN
    A_D_AIRPORT a ON c.iso_Country = a.iso_Country
GROUP BY
    c.iso_Country, c.name
);
