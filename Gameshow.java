// Saivenkat Jilla
// This program is a text-based adventure game, where you have to choose a class and make sound decisions when fighting 
// to come out on top and defeat the final boss.
// March 22, 2022

// Sidenote: mob just means a mobile object/monster/an nonplayer character vulnerable to attack.
// for the best experience in the Windows console, fullscreen it

import java.util.Scanner;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.AudioInputStream; // music
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Gameshow {
	static Scanner scan = new Scanner(System.in);
	static Random random = new Random(); // makes generating random numbers easier
	static Clip clip; // music
	static String name;
	static int treats, animalCount, totalATK = 0, totalDamageTaken = 0, totalPets = 0; // initialize constant variables
	static String[] animalNames = new String[5]; // when user tames a pet, stores the assigned pet name
	static int[] animalStats = new int[10]; // stores the stats of the pets in a hitpoints, attack fashion

	public static void main(String[] args) {
		music(4);
		String input;
		int screenInput = 0;
		boolean hasRun;
		hasRun = false;
		System.out.println("Townia and the Tyrant");
		System.out.println();
		print("Greetings, traveller.");
		name = getName();
		System.out.println();
		while (screenInput == 0) {
			if (hasRun == true) {
				System.out.println("Townia and the Tyrant");
				System.out.println();
				print("Greetings, " + name);
			}
			while (true) {
				print("Enter 1 to start.");
				print("Enter 2 to quit.");
				print("Enter 3 to save this session's statistics to a file, and then exit.");
				input = scan.nextLine();
				try {
					screenInput = Integer.parseInt(input.trim());
					while (screenInput != 1 && screenInput != 2 && screenInput != 3) {
						print("Enter 1 to start.");
						print("Enter 2 to quit.");
						print("Enter 3 to save this session's statistics to a file, and then exit.");
						input = scan.nextLine();
						screenInput = Integer.parseInt(input.trim());
					}
					break;
				} catch (Exception e) {
					print("That wasn't an integer!");
				}
			}
			if (screenInput == 1) {
				hasRun = true;
				towniatyrant();
			} else if (screenInput == 2) {
				print("Thanks for playing!");
				break;
			} else {
				statistics();
				break;
			}
			screenInput = 0; // resets screenInput to be 0 so when the user returns, while loop will run
								// again
		}
		scan.close();
	}

	// method towniatyrant()
	// called from main, a lot of worldbuilding and backstory is given here
	// post: user is aware if they died or beat the final boss
	public static void towniatyrant() {
		// game has 6 battles in total before final boss. turn based
		//
		music(1); // background music
		String numInput;
		int hitpoints, attack, defense, choice, encounters, tempHP;
		animalCount = 0;
		treats = 0;

		print("Hello " + name + ". Please pick a class.");
		print("As a warrior, get higher base stats, starting with 7 hitpoints and 5 attack.");
		print("Rangers can tame animals that they encounter in the wild. Start off with 5 hitpoints and 2 attack as an ranger.");
		print("The wizard class is a glass cannon. Start off with 4 hitpoints and 7 attack.");

		while (true) {
			print("Please indicate the class that you would like to choose. "
					+ "Input 1 for warrior, 2 for ranger, and 3"
					+ " for wizard.");
			numInput = scan.nextLine();
			try {
				choice = Integer.parseInt(numInput.trim());
				while (choice != 1 && choice != 2 && choice != 3) {
					System.out.println("Input 1 for warrior, 2 for ranger, and 3 for wizard.");
					numInput = scan.nextLine();
					choice = Integer.parseInt(numInput.trim());
				}
				break;
			} catch (Exception e) {
				print("That wasn't an integer!");
			}
		}

		if (choice == 1) {
			print("You are now a warrior. You open a loot chest to "
					+ "get supplies.");
			hitpoints = 7;
			attack = 5;
			int a = random.nextInt(100);
			if (a < 50) {
				print("You found a wooden sword!");
				System.out.println("Attack + 2!");
				attack = attack + 2;
			} else if (a < 90) {
				print("You found an iron mace!");
				System.out.println("Attack + 4!");
				attack = attack + 4;
			} else {
				print("The legendary sword Excalibur? Who put "
						+ "that in here?");
				System.out.println("Attack + 10!");
				attack = attack + 10;
			}
			int b = random.nextInt(100);
			if (b < 50) {
				print("You found some rusted armor. It appears to get the job done.");
				System.out.println("Defense + 1!");
				defense = 1;
			} else if (b < 90) {
				print("You found a chainmail chest piece!");
				System.out.println("Defense + 4!");
				defense = 4;
			} else {
				print("You found a full set of plate armor!");
				System.out.println("Defense + 10!");
				defense = 10;
			}
		} else if (choice == 2) {
			animalNames[0] = "Wolfie";
			animalCount++;
			print("You are now an ranger.");
			print("You start off with your loyal wolf companion, Wolfie.");
			System.out.println("Wolfie's stats: 4 hitpoints, 3 attack");
			animalStats[0] = 4; // animalStats array is in a hitpoints, attack fashion
			animalStats[1] = 3; // every two values represents one animal's stats
			treats = random.nextInt(10) + 2;
			print("You have " + treats + " treats. Use them wisely to level up pets after battles, "
					+ "or to tame more pets. You can have a maximum of 5 pets.");
			print("You open a loot chest to "
					+ "get supplies.");
			hitpoints = 5;
			attack = 2;
			int c = random.nextInt(100);
			if (c < 50) {
				print("You found a wooden spear!");
				System.out.println("Attack + 2!");
				attack = attack + 2;
			} else if (c < 90) {
				print("You found an iron sword!");
				System.out.println("Attack + 4!");
				attack = attack + 4;
			} else {
				print("The legendary sword Excalibur? Who put "
						+ "that in here?");
				System.out.println("Attack + 10!");
				attack = attack + 10;
			}
			int d = random.nextInt(100);
			if (d < 50) {
				print("You found some rusted armor. It appears to get the job done.");
				System.out.println("Defense + 1!");
				defense = 1;
			} else if (d < 90) {
				print("You found a chainmail chest piece!");
				System.out.println("Defense + 4!");
				defense = 4;
			} else {
				print("You found a full set of plate armor!");
				System.out.println("Defense + 10!");
				defense = 10;
			}
		} else {
			print("You're a wizard, " + name);
			print("As a wizard, you have access to fireball, a spell that costs 5 mana. It can deal up to 4x your "
					+ "usual attack! You only have access to 10 mana in any given battle, so use it wisely!");
			print("You open a loot chest to get supplies.");
			hitpoints = 4;
			attack = 7;
			int e = random.nextInt(100);
			if (e < 50) {
				print("You found a wooden staff!");
				System.out.println("Attack + 4!");
				attack = attack + 4;
			} else if (e < 90) {
				print("You found a silver wand!");
				System.out.println("Attack + 6!");
				attack = attack + 6;
			} else {
				print("It appears to be the Elder Wand.");
				System.out.println("Attack + 12!");
				attack = attack + 12;
			}
			int f = random.nextInt(100);
			if (f < 50) {
				print("You found a moldy bathrobe. It appears to get the job done.");
				System.out.println("Defense + 1!");
				defense = 1;
			} else if (f < 90) {
				print("You found some mage robes.");
				System.out.println("Attack + 2! Defense + 2!");
				attack = attack + 2;
				defense = 2;
			} else {
				print("You found Merlin's robes!");
				System.out.println("Attack + 10! Defense + 6!");
				attack = attack + 10;
				defense = 6;
			}
		}
		userDetails(hitpoints, attack, defense);

		print("A firedrake, Gregor the tyrant, has ravaged your town of Townia.");
		print("His lair is in a mountain cavern. Be brave " + name + ", and put an"
				+ " end to his evil.");
		print("With your new gear in hand, you set off to the nearby village of "
				+ "VillageTown, a rundown shanty at the foot of the mountain.");
		print("However, on your way to VillageTown...");
		print("You have been ambushed!");
		encounters = 1;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);
		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4); // different music between battle music

		print("You enter a tavern and indulge in a hearty meal. As you prepare to turn in for the night, a large "
				+ "shadow begins to loom over you...");
		encounters++;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);
		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4);

		if (levelChoice() == 1) {
			hitpoints = hitpoints + 2 + random.nextInt(5);
		} else {
			attack = attack + 2 + random.nextInt(3);
		}
		userDetails(hitpoints, attack, defense);

		print("With the adventures of the past night behind you, you begin your trek up the mountain. You suddenly"
				+ " hear some whimpers of pain. You rush towards the cries but it wasn't some poor helpless creature after "
				+ "all...");
		encounters++;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);
		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4);

		print("As night begins to set in, you look for a suitable place to rest. You find a cave "
				+ "tucked into the mountainside. You head inside and set up your tent. You then"
				+ " hear a thud and a screech, and the tent canvas is ripped into shreds.");
		encounters++;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);
		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4);

		if (levelChoice() == 1) {
			hitpoints = hitpoints + 2 + random.nextInt(5);
		} else {
			attack = attack + 2 + random.nextInt(3);
		}
		userDetails(hitpoints, attack, defense);

		print("When you wake up the next morning, you hear a tapping sound. It gets closer and closer "
				+ "until...");
		encounters++;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);
		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4);

		print("After that battle, you come ever closer to the firedrake's lair.");
		print("While walking on a precarious ledge, with the cavern in sight, an llama spots you.");
		print("It starts advancing menacingly...");
		encounters++;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);
		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4);

		if (levelChoice() == 1) {
			hitpoints = hitpoints + 2 + random.nextInt(5);
		} else {
			attack = attack + 2 + random.nextInt(3);
		}
		userDetails(hitpoints, attack, defense);

		print("You're almost there, " + name + "! Steeling your nerves, you head into cavern.");
		print("It seems as though no one's there...");
		print("You take a couple more steps into the lair.");
		print("You hear loud rumbling noises. Before you decide to leave the area and slap these "
				+ "responsibilities onto someone else, the mouth of the cavern collapses.");
		print("You're trapped...");
		print("To make matters worse, the lava pool in the middle of the cavern starts "
				+ "bubbling...");
		print("Gregor the Tyrant erupts from the lava!");
		print("Boss Battle!");
		encounters++;
		tempHP = randomEncounter(hitpoints, attack, defense, encounters, choice);

		if (healthChecker(tempHP) == 0) {
			return;
		}
		tempHP = hitpoints;
		music(4);

		print("With Gregor the Tyrant defeated, you return to Townia and are hailed as a hero. You live out the "
				+ "rest of your days in a fancy mansion, occassionally venturing out to kill a monster here or there...");
		print("Congratulations, " + name + "! Well played!");
		totalPets = totalPets + animalCount; // adds animalCount to totalPets before it is reset, used for statistics
												// method
		return;
	}

	// method getName()
	// called from main, gets the users name
	// post: user is assigned a name
	public static String getName() {
		String name;
		while (true) {
			try {
				print("What is your name?");
				name = scan.nextLine();
				break;
			} catch (Exception e) {
				print("Please enter a name.");
			}
		}
		name = name.trim();

		return name;
	}

	// method print()
	// called in various places, prints text in a cooler fashion using tiny time
	// delays
	// post: slightly better user experience
	public static void print(String text) {
		try {
			for (int i = 0; i < text.length(); i++) {
				System.out.print(text.charAt(i));
				Thread.sleep(12); // delays for 12 milliseconds between characters
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		try {
			Thread.sleep(125); // slightly longer delay between lines
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// method music()
	// called in various places, plays music
	// post: plays music corresponding to the predetermined choice
	public static void music(int choice) {
		String[] music = { "Background.wav", "Encounter.wav", "BossBattle.wav", "Bridge.wav" };

		try {
			if (clip != null) { // if another soundtrack is playing, stops it
				clip.stop();
				clip.close();
			}
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(music[(choice - 1)]).getAbsoluteFile());
			// gets the file name from the array
			clip = AudioSystem.getClip();
			clip.open(audioInputStream); // opens the clip
			clip.setLoopPoints(0, -1);
			clip.loop(Clip.LOOP_CONTINUOUSLY); // sets it to loop continuously
			clip.start();
		} catch (Exception e) {
			System.out.println("Error. Music cannot be played.");
			System.out.println();
		}

	}

	// method statistics()
	// called from main, saves session statistics to a file
	// post: user can check the total damage they've dealt and taken, as well as the
	// total pets tamed in this session
	public static void statistics() {
		try {
			// Create file if not made already
			FileWriter statistics = new FileWriter("statistics.txt", true);
			BufferedWriter out = new BufferedWriter(statistics);
			out.write(name + ":" + "\n");
			out.write("Total damage dealt: " + totalATK + "\n");
			out.write("Total damage taken: " + totalDamageTaken + "\n");
			out.write("Total pets tamed: " + totalPets + "\n");
			out.write("\n");
			// Close the output stream
			out.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	// method healthChecker()
	// called from towniatyrant(), checks if user hitpoints is 0
	// post: if temporary hitpoints are 0, user is told that they are returning to
	// the menu
	public static int healthChecker(int checker) {
		if (checker == 0) {
			System.out.println("Game over.");
			print("Returning to menu...");
			totalPets = totalPets + animalCount; // adds animalCount to totalPets before it is reset, used for
													// statistics method
			return checker;
		}
		return checker;
	}

	// method levelChoice()
	// called from towniatyrant(), lets the user choose what stat they want to level
	// up
	// post: the choice of the user will be returned
	public static int levelChoice() {
		String numInput;
		int levelChoice;
		print("With the experience you have gained from these battles, the magic of this world allows you to level "
				+ "up one of your traits.");
		while (true) {
			print("Please indicate the trait that you would like to level. "
					+ "Input 1 for increasing your hitpoints, 2 for increasing your attack.");
			numInput = scan.nextLine();
			try {
				levelChoice = Integer.parseInt(numInput.trim());
				while (levelChoice != 1 && levelChoice != 2) {
					System.out.println("Input 1 for increasing your hitpoints, 2 for increasing your attack.");
					numInput = scan.nextLine();
					levelChoice = Integer.parseInt(numInput.trim());
				}
				break;
			} catch (Exception e) {
				print("That wasn't an integer!");
			}
		}
		return levelChoice;
	}

	// method attack()
	// called from randomEncounter(), calculates the damage that will be dealt to
	// the enemy
	// post: attack is returned, modified if it was a critical hit
	public static int attack(int attack) {
		int critChance = random.nextInt(10);
		if (critChance <= 2) { // 30% critical chance
			attack = 2 * attack; // double damage if critical
			print("Critical hit!");
		}
		totalATK = totalATK + attack;
		return attack;
	}

	// method mobHealth()
	// called from randomEncounter(), calculates the health the mob has
	// post: the changed value for the health of the mob
	public static int mobHealth(int tempATK, int mobHP, String mobName) {
		mobHP = mobHP - tempATK;
		if (mobHP > 0) {
			print("The " + mobName + " still has " + mobHP + " hitpoints left!");
		}
		return mobHP;
	}

	// method petChoice()
	// called from randomEncounter(), asks the user to choose from a pet they
	// control to attack the enemy
	// post: returns the choice of the user
	public static int petChoice(int[] tempAnimalStats) {
		int petChoice;
		String petInput;
		while (true) {
			System.out.println();
			System.out.print("Choose 1 to command ");
			for (int a = 0; a < animalCount; a++) {
				if (a == 0) {
					System.out.print(animalNames[a]);
				} else if (a < (animalCount - 1)) {
					System.out.print(", " + (a + 1) + " to command " + animalNames[a]);
				} else {
					System.out.print(", or " + (a + 1) + " to command " + animalNames[a] + " to attack. ");
				}
			}

			petInput = scan.nextLine();
			try {
				petChoice = Integer.parseInt(petInput.trim());
				while ((petChoice != 1 && petChoice != 2 && petChoice != 3 && petChoice != 4 && petChoice != 5)
						|| (animalCount < petChoice) || (tempAnimalStats[(petChoice * 2 - 2)] == -1)) {
					// checks if the user input is valid or if the pet they command to attack is
					// dead
					if (tempAnimalStats[(petChoice * 2 - 2)] == -1) {
						print(animalNames[(petChoice - 1)] + " is dead. Please choose another pet to attack.");
					}
					System.out.println();
					System.out.print("Choose 1 to command ");
					for (int a = 0; a < animalCount; a++) {
						if (a == 0) {
							System.out.print(animalNames[a]);
						} else if (a < (animalCount - 1)) {
							System.out.print(", " + (a + 1) + " to command " + animalNames[a]);
						} else {
							System.out.print(", or " + (a + 1) + " to command " + animalNames[a] + " to attack. ");
						}
					}
					petInput = scan.nextLine();
					petChoice = Integer.parseInt(petInput.trim());
				}
				break;
			} catch (Exception e) {
				System.out.println("That wasn't an integer!");
			}
		}
		return petChoice;
	}

	// method userDetails()
	// displays the hitpoints, attack, and defense of the user
	public static void userDetails(int hitpoints, int attack, int defense) {
		print("You have " + hitpoints + " hitpoints, " + attack + " attack, and " + defense + " defense.");
	}

	// method petLevel()
	// if the user is a ranger, allows them to level up one of their pets after an
	// encounter
	// post: a pet has been levelled up or the user is informed that they chose to
	// hold on to their treats
	public static void petLevel() {
		String numInput;
		int choice;
		choice = 0;
		if (treats <= 0) {
			return;
		}

		print("You have " + treats + " treat(s). You are able to level up one of your pets for the cost of one treat.");
		while (true) {
			print("Choose 1 to feed a treat to a pet, or 2 to skip.");
			numInput = scan.nextLine();
			try {
				choice = Integer.parseInt(numInput.trim());
				while (choice != 1 && choice != 2) {
					System.out.println("Choose 1 to feed a treat to a pet, or 2 to skip.");
					numInput = scan.nextLine();
					choice = Integer.parseInt(numInput.trim());
				}
				break;
			} catch (Exception e) {
				print("That wasn't an integer!");
			}
		}
		if (choice == 2) {
			print("You decide to hold on to your treats...");
		}

		else {
			if (animalCount == 1) {
				print("Wolfie watches intently as you reach into your bag...");
				print("You toss Wolfie a treat, and a strange glow starts to envelop the animal.");
				int r = random.nextInt(5) + 2;
				print("Wolfie's stats have been increased by " + r + " !");
				animalStats[0] = animalStats[0] + r; // increases hp of Wolfie
				animalStats[1] = animalStats[1] + r; // increases atk of Wolfie
				print("Wolfie now has " + animalStats[0] + " hitpoints and " + animalStats[1] + " attack.");
			} else {
				print("Your pets watch intently as you reach into your bag...");
				int petChoice, confirmChoice;
				String petInput;

				while (true) {
					System.out.println();
					System.out.print("Choose 1 to feed ");
					for (int a = 0; a < animalCount; a++) {
						if (a == 0) {
							System.out.print(animalNames[a]);
						} else if (a < (animalCount - 1)) {
							System.out.print(", " + (a + 1) + " to feed " + animalNames[a]);
						} else {
							System.out.print(", or " + (a + 1) + " to feed " + animalNames[a] + ".");
						}
					}

					petInput = scan.nextLine();
					try {
						petChoice = Integer.parseInt(petInput.trim());
						while ((petChoice != 1 && petChoice != 2 && petChoice != 3 && petChoice != 4 && petChoice != 5)
								|| (animalCount < petChoice)) {
							// checks if the user input is valid
							System.out.println();
							System.out.print("Choose 1 to feed ");
							for (int a = 0; a < animalCount; a++) {
								if (a == 0) {
									System.out.print(animalNames[a]);
								} else if (a < (animalCount - 1)) {
									System.out.print(", " + (a + 1) + " to feed " + animalNames[a]);
								} else {
									System.out.print(", or " + (a + 1) + " to feed " + animalNames[a] + ". ");
								}
							}
							petInput = scan.nextLine();
							petChoice = Integer.parseInt(petInput.trim());
						}
						break;
					} catch (Exception e) {
						System.out.println("That wasn't an integer!");
					}
				}
				print("You have chosen to feed " + animalNames[(petChoice - 1)] + ". It's stats are "
						+ animalStats[(petChoice * 2 - 2)] + " hitpoints and " + animalStats[(petChoice * 2 - 1)]
						+ " attack.");
				while (true) {
					print("Choose 1 to confirm your choice, and 2 to choose another pet.");
					numInput = scan.nextLine();
					try {
						confirmChoice = Integer.parseInt(numInput.trim());
						while (confirmChoice != 1 && confirmChoice != 2) {
							System.out.println("Choose 1 to confirm your choice, and 2 to choose another pet.");
							numInput = scan.nextLine();
							confirmChoice = Integer.parseInt(numInput.trim());
						}
						break;
					} catch (Exception e) {
						System.out.println("That wasn't an integer!");
					}
				}
				if (confirmChoice == 2) {
					while (confirmChoice == 2) {
						print("You have chosen to feed " + animalNames[(petChoice - 1)] + ". It's stats are "
								+ animalStats[(petChoice * 2 - 2)] + " hitpoints and "
								+ animalStats[(petChoice * 2 - 1)] + " attack.");
						while (true) {
							print("Choose 1 to confirm your choice, and 2 to choose another pet.");
							numInput = scan.nextLine();
							try {
								confirmChoice = Integer.parseInt(numInput.trim());
								while (confirmChoice != 1 && confirmChoice != 2) {
									System.out.println("Choose 1 to confirm your choice, and 2 to choose another pet.");
									numInput = scan.nextLine();
									confirmChoice = Integer.parseInt(numInput.trim());
								}
								break;
							} catch (Exception e) {
								System.out.println("That wasn't an integer!");
							}
						}
					}
				}
				print("You toss " + animalNames[(petChoice - 1)]
						+ " a treat, and a strange glow starts to envelop the animal.");
				int r = random.nextInt(5) + 2;
				print("Wolfie's stats have been increased by " + r + " !");
				animalStats[(petChoice * 2 - 2)] = animalStats[(petChoice * 2 - 2)] + r; // increases hp of chosen pet
				animalStats[(petChoice * 2 - 1)] = animalStats[(petChoice * 2 - 1)] + r; // increases atk of chosen pet
				print(animalNames[(petChoice - 1)] + " now has " + animalStats[(petChoice * 2 - 2)] + " hitpoints and "
						+ animalStats[(petChoice * 2 - 1)] + " attack.");

			}
		}
		return;
	}

	// method randomEncounter()
	// called from towniatyrant(), is the battle experience of this game
	// post: user fights and either emerges victorious, runs away, or dies
	public static int randomEncounter(int userHP, int userATK, int userDEF, int randomCounter, int choice) {
		int mobHP, mobATK, fullMobHP, battleChoice, tempATK, petHP, petATK, moveChoice, petChoice, confirmChoice, mana;
		String mobName, numInput, petInput;
		String[] mobNames = { "manticore", "spider", "cougar", "griffin", "ghoul", "llama",
				"firedrake; Gregor the Tyrant" };
		String[] names = { "Molly", "Spencer", "Bob", "Gerald", "Cheeto", "Lollipop", "Greg" };
		boolean petAttack;
		int[] tempAnimalStats = animalStats.clone(); // copying elements of animalStats[] to tempAnimalStats[]
		mana = 10; // wizards start off with 10 mana at the beginning of the encounter
		petHP = 0;
		petChoice = 1;
		petAttack = false;

		if (randomCounter != 7) {
			music(2); // battle music
			mobHP = random.nextInt(randomCounter * 3) + 5;
			mobATK = random.nextInt(randomCounter * 2) + 2;
		} else {
			music(3); // boss music
			mobHP = 25; // boss stats
			mobATK = 15;
		}
		fullMobHP = mobHP; // stores the unmodified value in case user tames this mob
		mobName = mobNames[(randomCounter - 1)];
		print("It appears to be a wild " + mobName);

		while (mobHP > 0) {
			print("The " + mobName + " attacks!");
			if (petAttack == true) { // checks if a pet has attacked on your move
				print("The " + mobName + " lashes out at " + animalNames[(petChoice - 1)] + "!");
				print("It deals " + mobATK + " damage to " + animalNames[(petChoice - 1)] + "!");
				if (mobATK < petHP) {
					print(animalNames[(petChoice - 1)] + " survives the hit!");
					petHP = petHP - mobATK;
					System.out.println(animalNames[(petChoice - 1)] + " hitpoints remaining: " + petHP);
					tempAnimalStats[(petChoice * 2 - 2)] = petHP;
				} else {
					print(animalNames[(petChoice - 1)]
							+ " dies from the blow. Fight on, don't let its death be in vain!");
					tempAnimalStats[(petChoice * 2 - 2)] = -1;
				}
				petAttack = false; // resets to false so the enemy can attack the user if no pets attack next turn
			} else {
				print("The " + mobName + " deals " + mobATK + " damage to you!");
				totalDamageTaken = totalDamageTaken + mobATK;
				if (mobATK < userDEF) {
					print("Your armor takes the brunt of the attack!");
					userDEF = userDEF - mobATK;
					System.out.println("Defense remaining: " + userDEF);
				} else if (mobATK >= userDEF && mobATK < (userDEF + userHP) && (userDEF != 0)) {
					print("Your armor has been disabled.");
					userHP = userHP + (userDEF - mobATK);
					System.out.println("Your new life count is " + userHP);
					userDEF = 0;
				} else if ((mobATK < userHP) && (userDEF == 0)) {
					userHP = userHP + (userDEF - mobATK);
					System.out.println("Your new life count is " + userHP);
				} else if (mobATK >= (userDEF + userHP)) {
					print("You have fallen. Better luck next time.");
					userHP = 0;
					return userHP;
				}
			}
			while (true) {
				userDetails(userHP, userATK, userDEF);
				print("Choose 1 to fight or 2 to run.");
				numInput = scan.nextLine();
				try {
					battleChoice = Integer.parseInt(numInput.trim());
					while (battleChoice != 1 && battleChoice != 2) {
						System.out.println("Choose 1 to fight or 2 to run.");
						numInput = scan.nextLine();
						battleChoice = Integer.parseInt(numInput.trim());
					}
					break;
				} catch (Exception e) {
					System.out.println("That wasn't an integer!");
				}
			}
			if (battleChoice == 1) {
				if (choice == 2) {
					print("You have " + treats + " treat(s) and " + animalCount + " pets.");
					while (true) {
						print("Choose 1 to attack with your weapon, 2 to attempt to tame the " + mobName + ", or "
								+ "3 to fight with a pet.");
						petInput = scan.nextLine();
						try {
							moveChoice = Integer.parseInt(petInput.trim());
							while (moveChoice != 1 && moveChoice != 2 && moveChoice != 3) {
								System.out.println("Choose 1 to attack with your weapon, 2 to attempt to tame the "
										+ mobName + ", or "
										+ "3 to command a pet to fight.");
								petInput = scan.nextLine();
								moveChoice = Integer.parseInt(petInput.trim());
							}
							break;
						} catch (Exception e) {
							System.out.println("That wasn't an integer!");
						}
					}
					if (moveChoice == 1) {
						tempATK = userATK;
						tempATK = attack(tempATK);
						print("You deal " + tempATK + " damage!");
						mobHP = mobHealth(tempATK, mobHP, mobName);
					} else if (moveChoice == 2) {
						if (animalCount == 5) {
							print("You already have 5 pets!");
						} else {
							if (treats == 0) {
								print("Seems like you forgot you didn't have any treats left. Brace yourself!");
							} else {
								treats = treats - 1;
								print("After using a treat, you now have " + treats + " left.");
								print("The " + mobName + " sniffs at the treat...");
								int petTamed = 0;
								petTamed = random.nextInt(10);
								if (petTamed <= 4) { // 50% pet taming chance
									print("The " + mobName + " has been tamed!");
									animalCount++;
									for (int j = 0; j <= 4; j++) {
										if (animalNames[j] == null) {
											animalNames[j] = names[(randomCounter - 1)];
											animalStats[(j * 2)] = fullMobHP;
											animalStats[((j * 2) + 1)] = mobATK;
											if (randomCounter != 7) {
												print(animalNames[j] + " has been tamed! "
														+ "It's stats are " + animalStats[(j * 2)]
														+ " hitpoints and " + animalStats[((j * 2) + 1)]
														+ " attack.");
											} else {
												print("You have tamed the final boss. Congratulations!");
												print("You rename Gregor the Tyrant to just Greg. Greg's stats"
														+ " are " + animalStats[(j * 2)]
														+ " hitpoints and " + animalStats[((j * 2) + 1)]
														+ " attack.");
											}
											return userHP;
										}
									}
								} else {
									print("The " + mobName + " isn't interested in the treat. Brace yourself!");
								}
							}
						}
					}

					else {
						int checker = 0 - animalCount;
						checker = checker - (tempAnimalStats[0] + tempAnimalStats[2] + tempAnimalStats[4]
								+ tempAnimalStats[6] + tempAnimalStats[8]);
						// checking if the number of dead pets is equal to the total number of pets
						if (checker == 0) {
							print("All of your pets are dead. Condolences.");
						} else {
							petAttack = true;
							if (animalCount == 1) { // if only one pet is owned, then that is the starter pet
								print("Wolfie goes on the attack!");
								petChoice = 1;
								petHP = tempAnimalStats[0];
								petATK = tempAnimalStats[1];
								tempATK = petATK;
								tempATK = attack(tempATK);
								print("Wolfie deals " + tempATK + " damage!");
								mobHP = mobHealth(tempATK, mobHP, mobName);
							}

							else {
								print("You have " + animalCount + " pets. Choose a pet that will attack this turn.");
								petChoice = petChoice(tempAnimalStats);
								print("You have selected " + animalNames[(petChoice - 1)]
										+ " to attack. It's stats are "
										+ tempAnimalStats[(petChoice * 2 - 2)] + " hitpoints and "
										+ tempAnimalStats[(petChoice * 2 - 1)] + " attack.");
								while (true) {
									print("Choose 1 to confirm your choice, and 2 to choose another pet.");
									numInput = scan.nextLine();
									try {
										confirmChoice = Integer.parseInt(numInput.trim());
										while (confirmChoice != 1 && confirmChoice != 2) {
											System.out.println(
													"Choose 1 to confirm your choice, and 2 to choose another pet.");
											numInput = scan.nextLine();
											confirmChoice = Integer.parseInt(numInput.trim());
										}
										break;
									} catch (Exception e) {
										System.out.println("That wasn't an integer!");
									}
								}
								if (confirmChoice == 2) {
									while (confirmChoice == 2) {
										petChoice = petChoice(tempAnimalStats);
										print("You have selected " + animalNames[(petChoice - 1)]
												+ " to attack. It's stats are "
												+ tempAnimalStats[(petChoice * 2 - 2)] + " hitpoints and "
												+ tempAnimalStats[(petChoice * 2 - 1)] + " attack.");
										while (true) {
											print("Choose 1 to confirm your choice, and 2 to choose another pet.");
											numInput = scan.nextLine();
											try {
												confirmChoice = Integer.parseInt(numInput.trim());
												while (confirmChoice != 1 && confirmChoice != 2) {
													System.out.println(
															"Choose 1 to confirm your choice, and 2 to choose another pet.");
													numInput = scan.nextLine();
													confirmChoice = Integer.parseInt(numInput.trim());
												}
												break;
											} catch (Exception e) {
												System.out.println("That wasn't an integer!");
											}
										}
									}
								}
								print(animalNames[(petChoice - 1)] + " goes on the attack!");
								petHP = tempAnimalStats[(petChoice * 2 - 2)];
								petATK = tempAnimalStats[(petChoice * 2 - 1)];
								tempATK = petATK;
								tempATK = attack(tempATK);
								print(animalNames[(petChoice - 1)] + " deals " + tempATK + " damage!");
								mobHP = mobHealth(tempATK, mobHP, mobName);
							}
						}
					}
				} else if (choice == 3) {
					if (mana >= 5) {
						print("You have " + mana + " mana. Would you like to cast a fireball? Choose 1"
								+ " to do a basic wand attack, and 2 to cast a fireball for 5 mana.");
						while (true) {
							numInput = scan.nextLine();
							try {
								moveChoice = Integer.parseInt(numInput.trim());
								while (moveChoice != 1 && moveChoice != 2) {
									System.out.println("Choose 1 to  do a basic wand attack, and 2 to cast a"
											+ " fireball for 5 mana.");
									numInput = scan.nextLine();
									moveChoice = Integer.parseInt(numInput.trim());
								}
								break;
							} catch (Exception e) {
								System.out.println("That wasn't an integer!");
							}
						}
						if (moveChoice == 2) {
							print("You cast a fireball!");
							mana = mana - 5;
							tempATK = 2 * userATK; // fireball does 2x the users damage
							tempATK = attack(tempATK); // chance to crit for 4x user damage
							print("You deal " + tempATK + " damage!");
							mobHP = mobHealth(tempATK, mobHP, mobName);
						}

						else {
							print("You perform a basic wand attack.");
							tempATK = userATK;
							tempATK = attack(tempATK);
							print("You deal " + tempATK + " damage!");
							mobHP = mobHealth(tempATK, mobHP, mobName);
						}
					} else {
						print("You perform a basic wand attack.");
						tempATK = userATK;
						tempATK = attack(tempATK);
						print("You deal " + tempATK + " damage!");
						mobHP = mobHealth(tempATK, mobHP, mobName);
					}

				}

				else { // user is not an ranger or a mage -> warrior
					tempATK = userATK;
					tempATK = attack(tempATK);
					print("You deal " + tempATK + " damage!");
					mobHP = mobHealth(tempATK, mobHP, mobName);
				}
			} else if (battleChoice == 2) { // user has chosen to flee
				if (randomCounter <= 4) {
					print("You have successfully escaped.");
					return userHP; // allows user to skip up to the first 4 battles
				} else {
					print("The " + mobName + " has you cornered. You cannot run.");
				}
			}
		}
		print("You win!");
		System.out.println("The magic of this world restores your armor and brings your lifecount to max, "
				+ "also reviving any pets you may have.");
		System.out.println();
		return userHP;
	}
}
