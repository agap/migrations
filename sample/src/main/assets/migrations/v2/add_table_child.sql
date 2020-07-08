create table child (
    id integer primary key not null, 
    name text, 
    parentId integer not null, 
    foreign key (parentId) references parent (id) on update no action on delete cascade
);