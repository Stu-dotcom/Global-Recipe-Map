import React, { useState, useEffect } from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import RecipeForm from './RecipeForm';

const recipeMarkerIcon = new L.Icon({
    iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-icon.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
});

const plusMarkerIcon = new L.Icon({
    iconUrl: 'data:image/svg+xml;charset=UTF-8,' + encodeURIComponent(`
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 64" width="32" height="32">
            <circle cx="32" cy="32" r="32" fill="green" />
            <line x1="32" y1="16" x2="32" y2="48" stroke="white" stroke-width="6"/>
            <line x1="16" y1="32" x2="48" y2="32" stroke="white" stroke-width="6"/>
        </svg>
    `),
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32],
});

const MapComponent = () => {
    const [recipes, setRecipes] = useState([]); // Store recipes from API
    const [formCoordinates, setFormCoordinates] = useState({ latitude: null, longitude: null }); // Coordinates for form

    // Function to fetch recipes from the API
    const fetchRecipes = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/recipes');
            if (response.ok) {
                const data = await response.json();
                setRecipes(data); // Store recipes in state
                setFormCoordinates({ latitude: null, longitude: null }); // Clear coordinates to remove the plus marker
            } else {
                console.error('Failed to fetch recipes:', response.statusText);
            }
        } catch (error) {
            console.error('Error fetching recipes:', error);
        }
    };

    // Fetch recipes on component mount
    useEffect(() => {
        fetchRecipes();
    }, []);

    // Custom component to handle map click events and update form coordinates
    const MapClickHandler = () => {
        useMapEvents({
            click: (e) => {
                setFormCoordinates({ latitude: e.latlng.lat, longitude: e.latlng.lng });
                console.log("Map clicked at latitude:", e.latlng.lat, "longitude:", e.latlng.lng);
            },
        });
        return null;
    };

    return (
        <div style={{ position: 'relative', height: '100vh' }}>
            <MapContainer center={[51.505, -0.09]} zoom={13} style={{ height: '100%', width: '100%' }}>
                <TileLayer
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    attribution="&copy; <a href='https://www.openstreetmap.org/copyright'>OpenStreetMap</a> contributors"
                />

                {/* Handle map clicks for updating form coordinates */}
                <MapClickHandler />

                {/* Display markers for each recipe */}
                {recipes.map((recipe) => (
                    <Marker
                        key={recipe.id}
                        position={[recipe.latitude, recipe.longitude]}
                        icon={recipeMarkerIcon}
                    >
                        <Popup>
                            <h3>{recipe.name}</h3>
                            <p>{recipe.description}</p>
                            <p><strong>Ingredients:</strong> {recipe.ingredients.join(', ')}</p>
                        </Popup>
                    </Marker>
                ))}

                {/* Display a plus marker to show where the next recipe will be added */}
                {formCoordinates.latitude && formCoordinates.longitude && (
                    <Marker
                        position={[formCoordinates.latitude, formCoordinates.longitude]}
                        icon={plusMarkerIcon}
                    >
                        <Popup>Next recipe location</Popup>
                    </Marker>
                )}
            </MapContainer>

            {/* Pass the form coordinates and refresh function to RecipeForm */}
            <div style={{ position: 'absolute', top: 10, left: 10, zIndex: 1000 }}>
                <RecipeForm
                    latitude={formCoordinates.latitude}
                    longitude={formCoordinates.longitude}
                    onRecipeSubmit={fetchRecipes} // Trigger a refresh after submission
                />
            </div>
        </div>
    );
};

export default MapComponent;
