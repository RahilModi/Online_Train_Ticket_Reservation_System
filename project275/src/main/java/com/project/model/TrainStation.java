package com.project.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TrainStation implements Serializable{

	@ManyToOne
	private Station station;
	
	@ManyToOne
	private Train train;

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainStation that = (TrainStation) o;

        if (train != null ? !train.equals(that.train) : that.train != null) return false;
        if (station != null ? !station.equals(that.station) : that.station != null)
            return false;

        return true;
    }
	public int hashCode() {
        int result;
        result = (train != null ? train.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        return result;
    }
	
	
}
