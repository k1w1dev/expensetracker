CREATE TABLE IF NOT EXISTS bank_account (
    id UUID PRIMARY KEY,
    bank_id text,
    account_id text,
    account_type text
);

CREATE TABLE IF NOT EXISTS transaction (
    id UUID PRIMARY KEY,
    bank_account_id UUID,
    txn_date DATE,
    fit_id text,
    type text,
    amount text,
    payee text,
    memo text
);