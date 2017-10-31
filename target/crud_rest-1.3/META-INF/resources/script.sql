/**
 * Author:  everton
 * Created: 31/10/2017
 */

create table public.chamado
(
	id bigserial not null,
	assunto character varying (256)not null,
	status character varying (32) not null,
	mensagem character varying (20248)not null,
	primary key (id)
)
with(
	oids = false
);