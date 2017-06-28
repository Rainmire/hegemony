# hegemony
Resource management demo, work in progress
Manage your resources and build your empire!

TechNode:
contains information on the level of each technology, as well as info about its children, which require the parent tech to be a certain level to unlock.

TechTree:
Holds all of a user's game data, including resources and tech. A tree of TechNodes is constructed upon TechTree initialization and each node mapped to their name.

gsonReadWrite:
Handles saves and loads by using Gson. Converts a TechTree to a json string and writes it to a file, or loads a json string and converts it to a TechTree.

Game:
Game initialization, UI, and timestamp. Calls gsonReadWrite to fetch save data or initializes new TechTree. Prompts user for upgrades. Creates an Instant whenever UI is refreshed or game is saved, and calculates the difference between current and previous timestamp to update resources accordingly.
