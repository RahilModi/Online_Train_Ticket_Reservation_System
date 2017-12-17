package com.project.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum Station implements Serializable{

	A(1),B(2),C(3),D(4),E(5),F(6),G(7),H(8),I(9),J(10),K(11),L(12),M(13),N(14),O(15),
		P(16),Q(17),R(18),S(19),T(20),U(21),V(22),W(23),X(24),Y(25),Z(26);
	
	@Id
	private int value;
	
	private Station()
	{
	}
	private Station(int value)
	{
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	private static Map<Integer, Station> map = new HashMap<>();

    static {
        for (Station s : Station.values()) {
            map.put(s.value, s);
        }
    }

    public static Station valueOf(int value) {
        return (Station) map.get(value);
    }
}
