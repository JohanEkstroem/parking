import React, { useEffect, useState } from 'react';
import styles from './css/Form.module.css';
import { useNavigate } from 'react-router-dom';
import jwt_decode from 'jwt-decode';

const Parking = () => {
  const [cars, setCars] = useState([]);
  const [parkingSpot, setParkingSpot] = useState([]);

  const [stoptime, setStoptime] = useState('');
  const [carNum, setCarNum] = useState('');
  const [parkingLocation, setParkingLocation] = useState('');
  const navigate = useNavigate();

  const token = localStorage.getItem('fungover');
  const decoded = jwt_decode(token);
  const userName = decoded.sub;

  useEffect(() => {
    const getAllCarsToUsername = async () => {
      const settings = {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('fungover'),
        },
      };
      const result = await fetch(
        'http://localhost:8080/api/cars/' + userName,
        settings
      );
      const data = await result.json();
      setCars([...data]);
    };
    getAllCarsToUsername();
  }, []);

  useEffect(() => {
    const getAllParkingSpots = async () => {
      const settings = {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('fungover'),
        },
        method: 'GET',
      };
      const result = await fetch(
        'http://localhost:8080/api/parkingspot',
        settings
      );
      const data = await result.json();
      setParkingSpot([...data]);
    };
    getAllParkingSpots();
  }, []);

  const handleParking = async () => {
    const settings = {
      headers: {
        Accept: 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('fungover'),
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify({
        stoptime,
        car: {
          id: carNum,
        },
        parkingSpot: {
          id: parkingLocation,
        },
      }),
    };
    try {
      const response = await fetch(
        'http://localhost:8080/api/parkingevent',
        settings
      );
      if (response.status === 201) {
        navigate('/');
      } else {
        navigate('/ops');
      }
    } catch (e) {
      console.log(e);
    }
  };
  return (
    <>
      <div className={styles.container}>
        <h2>Parking</h2>
        <form className={styles.parkingForm}>
          <input
            type="datetime-local"
            placeholder="Stoptime"
            onChange={(e) => setStoptime(e.target.value)}
          />
          <select
            id="ddlProducts"
            name="Car"
            onChange={(e) => setCarNum(e.target.value)}
          >
            <option>Choose car</option>
            {cars.map((item, index) => {
              return (
                <option key={index} value={item.id}>
                  {item.registrationNumber}
                </option>
              );
            })}
          </select>
          <select onChange={(e) => setParkingLocation(e.target.value)}>
            <option>Choose parkingspot</option>
            {parkingSpot.map((item, index) => {
              return (
                <option key={index} value={item.id}>
                  {item.id}
                </option>
              );
            })}
          </select>

          <input
            type="button"
            value="Submit"
            className={styles.submitBtn}
            onClick={handleParking}
          />
        </form>
      </div>
    </>
  );
};

export default Parking;
