create table if not exists ct_user_permission (
  id_user int8 not null,
  id_permission int8 NOT NULL,
  primary key (id_user, id_permission),
  constraint fk_user_permission foreign key (id_user) REFERENCES ct_user(id),
  constraint fk_user_permission_permission foreign key (id_permission) REFERENCES ct_permission(id)
);