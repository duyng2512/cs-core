DROP TABLE CONTR_STATUS CASCADE CONSTRAINTS;

CREATE TABLE STATUS
(
  AMND_STATE        VARCHAR2(1 CHAR)            DEFAULT 'A',
  AMND_DATE         DATE                        DEFAULT sysdate,
  AMND_OFFICER      NUMBER(18),
  AMND_PREV         NUMBER(18),
  ID                NUMBER(18)                  NOT NULL,
  CON_CAT           VARCHAR2(1 CHAR),
  NAME              VARCHAR2(255 CHAR),
  CODE              VARCHAR2(2 CHAR),
  EXTERNAL_CODE     VARCHAR2(32 CHAR),
  IS_VALID          VARCHAR2(1 CHAR),
  ACTION_TYPE       VARCHAR2(32 CHAR),
  RESTRICTION_CODE  VARCHAR2(32 CHAR),
  PRIORITY_CODE     VARCHAR2(32 CHAR),
  FRAUD_TYPE        VARCHAR2(32 CHAR),
  AUTH_RC           NUMBER(9)                   DEFAULT 0                     NOT NULL,
  CODE_PARMS        VARCHAR2(255 CHAR)
);


TABLESPACE OWCONST_D
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE;


CREATE UNIQUE INDEX PK_CONTR_STATUS ON CONTR_STATUS
(ID)
LOGGING
TABLESPACE OWCONST_I
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );

ALTER TABLE CONTR_STATUS ADD (
  CONSTRAINT PK_CONTR_STATUS
  PRIMARY KEY
  (ID)
  USING INDEX PK_CONTR_STATUS
  ENABLE VALIDATE);


CREATE INDEX IP_CONTR_STATUS ON CONTR_STATUS
(AMND_PREV)
LOGGING
TABLESPACE OWCONST_I
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );

DROP SEQUENCE CONTR_STATUS_SEQ;

CREATE SEQUENCE CONTR_STATUS_SEQ
  START WITH 2200
  INCREMENT BY 10
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
  NOKEEP
  NOSCALE
  GLOBAL;


CREATE OR REPLACE TRIGGER CONTR_STATUS_TIBS
  BEFORE INSERT ON CONTR_STATUS
  for each row
begin
  IF :new.ID IS NULL THEN
    if oracle_trgsupp.EnabledTo is null or sysdate >= oracle_trgsupp.EnabledTo then
      oracle_trgsupp.CHECK_ENABLED ('CONTR_STATUS');
    end if;
    SELECT CONTR_STATUS_SEQ.NEXTVAL INTO :new.ID FROM DUAL;
    oracle_trgsupp.LastId := :new.ID;
  END IF;
  IF :new.amnd_prev IS NULL THEN
    :new.amnd_prev := :new.ID;
  END IF;
  IF :new.amnd_officer IS NULL OR :new.amnd_officer = 0 THEN
    :new.amnd_officer := oracle_trgsupp.OfficerId;
  END IF;
end;
/


GRANT DELETE, INSERT, UPDATE ON CONTR_STATUS TO MAFCR1;

GRANT DELETE, INSERT, SELECT, UPDATE ON CONTR_STATUS TO MAFCR1130;

GRANT SELECT ON CONTR_STATUS TO MAFCR1130U;

GRANT DELETE, INSERT, SELECT, UPDATE ON CONTR_STATUS TO MAFCR1730;

GRANT SELECT ON CONTR_STATUS TO MAFCR1730U;

GRANT INSERT, SELECT, UPDATE ON CONTR_STATUS TO MAFCROWN;

GRANT SELECT ON CONTR_STATUS TO MAFCRSEL;

GRANT SELECT ON CONTR_STATUS TO MAFCRW4CS;
