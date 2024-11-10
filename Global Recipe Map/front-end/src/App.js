import React from 'react';
import './App.css';
import MapComponent from './components/MapComponent';

function App() {
  return (
      <div className="App">
        <h1>Leaflet Map with OpenStreetMap Tiles</h1>
        <MapComponent/>
      </div>
  );
}

export default App;
