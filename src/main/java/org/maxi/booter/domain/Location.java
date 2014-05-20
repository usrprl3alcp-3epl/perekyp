package org.maxi.booter.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.maxi.booter.domain.car.Car;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
//public class Location extends AbstractPersistable<Long> {
public class Location extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "definition.location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Car> cars;
	
	@OneToMany(mappedBy = "definition.location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Subscription> subscriptions;

	@Column(nullable = false)
	private String name;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
}
