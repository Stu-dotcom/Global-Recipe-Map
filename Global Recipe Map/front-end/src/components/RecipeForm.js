import React, { useState } from 'react';
import './RecipeForm.css';

const RecipeForm = ({ onSubmit }) => {
    const [isVisible, setIsVisible] = useState(false);
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        ingredients: '',
        location: '',
        userId: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Convert comma-separated ingredients to an array
        const recipeWithArrayIngredients = {
            ...formData,
            ingredients: formData.ingredients.split(',').map(ingredient => ingredient.trim()), // Split by comma and trim spaces
        };

        onSubmit(recipeWithArrayIngredients);
        setFormData({ name: '', description: '', ingredients: '', location: '', userId: '' });
        setIsVisible(false);
    };

    return (
        <>
            <button onClick={() => setIsVisible(true)} className="open-form-button">Add Recipe</button>

            {isVisible && (
                <div className="form-overlay">
                    <div className="form-container">
                        <button onClick={() => setIsVisible(false)} className="close-form-button">&times;</button>
                        <h2>Submit a Recipe</h2>
                        <form onSubmit={handleSubmit}>
                            <label>
                                Name:
                                <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                            </label>
                            <label>
                                Description:
                                <textarea name="description" value={formData.description} onChange={handleChange} required></textarea>
                            </label>
                            <label>
                                Ingredients (comma separated):
                                <input type="text" name="ingredients" value={formData.ingredients} onChange={handleChange} required />
                            </label>
                            <label>
                                Location:
                                <input type="text" name="location" value={formData.location} onChange={handleChange} required />
                            </label>
                            <label>
                                User ID:
                                <input type="number" name="userId" value={formData.userId} onChange={handleChange} required />
                            </label>
                            <button type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            )}
        </>
    );
};

export default RecipeForm;