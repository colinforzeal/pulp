package com.pulp.user.model.user_connection_model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "userconnection")
public class UserConnection extends AbstractUserConnectionWithCompositeKey {

	private static final long serialVersionUID = 1L;

}
