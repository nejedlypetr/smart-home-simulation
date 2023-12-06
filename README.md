# Smart home simulation

Every house have a special room called a "Heat pump room" with a Heat pump that regulates the house temperature. There is no need to configure this special room, it is set up automatically.

## House configuration
Create a `house.json` file with your custom configuration. A house configuration is automatically loaded from house.json if it is present; if not, a default house configuration is used. All parameters in the JSON file are mandatory (only the `"sensor"` parameter is optional). Follow the structure outlined below:
```json
{
  "pricePerKWh": 4.99, // Czech crowns
  "creatures": [
    {
      "name": "Alice",
      "type": "Adult" // Allowed types are: "Adult", "Child", "Baby", "Cat", "Dog"
    }
  ],
  "sportEquipment": ["Ski", "Bicycle"], // Allowed types are: "Ski", "Bicycle"
  "floors": [
    {
      "name": "First floor",
      "rooms": [
        {
          "name": "Living room",
          "sensor": "Normal sensor", // This parameter is optional, allowed types are: "Normal sensor", "Crazy sensor"
          "devices": ["TV", "Phone"], // Allowed types are: "Car", "Dish washer", "Fridge", "Laptop", "Light bulb", "Phone", "TV", "Washing machine"
          "activities": [
            {
              "type": "Creature", // Allowed types are: "Creature", "Adult", "Child", "Baby", "Cat", "Dog"
              "description": " is sleeping." // Note: Put a blank character before the text to ensure a correct report output.
            }
          ]
        }
      ]
    }
  ]
}
```

**Note:** If you choose to use the JSON file example above, please remove all comments before proceeding.

### Complex house.json example
You can find a complex example `house.json` file in the root folder of this repository.