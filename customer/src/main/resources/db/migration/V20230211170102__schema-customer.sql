create table customer
(
    kode         varchar(64) not null primary key,
    user_id      varchar(25) not null,
    nama_lengkap varchar(50),
    alamat       text
);

insert into customer(kode, user_id, nama_lengkap, alamat)
values ('cust01', 'dimasm93', 'Dimas Maryanto', 'Bandung, Jawa Barat');
