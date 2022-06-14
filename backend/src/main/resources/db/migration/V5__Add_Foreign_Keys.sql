alter table if exists ct_investor add constraint FKk1kty64phf4j7enysv06fnh6w foreign key (user_id) references ct_user;

alter table if exists ct_operation add constraint FKgf51r7ufrjlgr2a43lr5j86y8 foreign key (portfolio_id) references ct_portfolio on delete cascade;

alter table if exists ct_portfolio add constraint FKee6j16nuaaarme5hxc06gn1yw foreign key (investor_id) references ct_investor;