const ParkingEvent = () => {
  return (
    <>
      <div className="container">
        <h2>Parking</h2>
        <form>
          <label>
            <input type="datetime-local" placeholder="Stoptime" />
          </label>
          <label>
            <input type="number" placeholder="Car" />
          </label>
          <label>
            <input type="number" placeholder="Parking location" />
          </label>
          <input type="submit" value="Submit" />
        </form>
      </div>
    </>
  );
};

export default ParkingEvent;
