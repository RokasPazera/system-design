# Talking script: System Design defence

Rokas Pazera (551495). Deck: `RokasPazera551495_presentation.pptx`, 13 slides.

The script has 971 words. At a calm pace of 130 to 140 words per minute it takes about 7 minutes, inside the 5 to 8 minute limit. The same text is in the speaker notes of every slide, so Presenter View shows it too.

Before the defence: have the report of EA1, the report of EA2 and the EA2 code open. The examiners may ask you to show details.

## Timing

| # | Slide | Target | Ends at |
|---|---|---|---|
| 1 | Title | 15 s | 0:15 |
| 2 | Exam assignment 1: Battle Engine | 10 s | 0:25 |
| 3 | EA1: C4 Layer 1 (Context) | 35 s | 1:00 |
| 4 | EA1: C4 Layer 2 (Containers) | 45 s | 1:45 |
| 5 | EA1: C4 Layer 3 (Components) | 40 s | 2:25 |
| 6 | EA1: C4 Layer 4 (Interface and facade) | 45 s | 3:10 |
| 7 | EA1: C4 Layer 4 (Actions, effects and validation) | 40 s | 3:50 |
| 8 | Exam assignment 2: 3D printer case | 10 s | 4:00 |
| 9 | EA2: Code analysis | 45 s | 4:45 |
| 10 | EA2: (Re)design (Components and printers) | 40 s | 5:25 |
| 11 | EA2: (Re)design (Scheduling and strategy) | 30 s | 5:55 |
| 12 | EA2: Refactored code | 35 s | 6:30 |
| 13 | Takeaways | 20 s | 6:50 |

Total: about 6:50.

## Slide 1: Title (0:00 to 0:15)

Good morning. I am Rokas Pazera, student 551495. In the next seven minutes I present my two System Design assignments: first the Battle Engine, designed with the four layers of C4, then the refactoring of the 3D print scheduler.

## Slide 2: Exam assignment 1: Battle Engine (0:15 to 0:25)

The Battle Engine runs one turn-based battle between the human player and the computer. It is a rules engine only: no user interface, no AI and no storage.

## Slide 3: EA1: C4 Layer 1 (Context) (0:25 to 1:00)

*Left to right: the context diagram, then the two use case diagrams.*

At layer 1 the whole online game is one software system. The Player is the only user, and the existing Account Provider is the only external system. It is used to log in.

The use case diagram in the middle shows what a player can do. On the right I made the Battle Engine itself the subject. The other parts of the game instance are its actors, and its six interface methods are the use cases. Apply action always includes validate action.

## Slide 4: EA1: C4 Layer 2 (Containers) (1:00 to 1:45)

*Point at the purple Game Instance, then at the deployment diagram.*

Layer 2 splits the system by how the game is played: always online, instances that start and stop, and at most eight players per instance. That gives five containers. The Game Instance, in purple, runs the Battle Engine and keeps its data only in memory.

The deployment diagram shows one container per instance on auto-scaled game servers, so a crashing instance cannot affect another one. Between the client and the instance I chose a WebSocket, because the server must push the moves of the computer. For the services I chose REST over HTTPS, because those are short request and response calls.

## Slide 5: EA1: C4 Layer 3 (Components) (1:45 to 2:25)

*Point at each numbered card as you name the decision.*

Layer 3 zooms in on the Game Instance and shows who uses the engine. Three decisions matter here.

One: the Battle Coordinator owns the turn loop. Only it starts a battle, passes the turn and saves the result, so the AI can never pass the turn of the human player.

Two: the human and the AI controller provide the same BattleController interface, so the coordinator treats both players the same way.

Three: the Battle Broadcaster is a decorator around the engine. It sends every new state to the client, so the engine itself knows nothing about networking.

## Slide 6: EA1: C4 Layer 4 (Interface and facade) (2:25 to 3:10)

*Point at the two small interfaces at the top of the diagram.*

Layer 4 is the focus of this assignment. I split the six methods into two interfaces: BattleActions for the controllers and BattleLifecycle for the coordinator. Now the compiler stops a controller from calling nextTurn.

BattleEngineFacade is the only implementation. It hides the factory, the turn manager, the validator and the resolver, so a caller does not need to know the order of the steps.

I considered a Singleton and rejected it, because up to eight players can fight at the same time. Every battle gets its own facade. And getBattleState returns a deep copy, so no live object leaves the engine and every change goes through the rules.

## Slide 7: EA1: C4 Layer 4 (Actions, effects and validation) (3:10 to 3:50)

*Left diagram first, then the list of rules on the right.*

On the left: ActionDefinition is what a unit can do, Action is only the request with ids. The effects use the Strategy pattern: damage, heal and stat change each implement ActionEffect. An action holds a list of effects, so an action like hit and lower defence needs no new code. The alternative, one Action class with a switch, changes with every new type.

On the right: applyAction first runs a fixed list of rules. The first violation throws an InvalidActionException, so a battle is never half changed. A new rule is one new class in the list.

## Slide 8: Exam assignment 2: 3D printer case (3:50 to 4:00)

The second assignment is the 3D print scheduler. It tells Jack which print to start on which printer. I analysed the code, redesigned it and refactored it.

## Slide 9: EA2: Code analysis (4:00 to 4:45)

*Point at the long method list of PrinterManager.*

This is the original class diagram. I found three main problems.

One: two god classes and no separation. Main mixes the menu with file reading, and PrinterManager does everything else. Its selectPrintTask method was 110 lines with six levels of nesting.

Two: printer types are not used as types. instanceof branches in PrinterManager decide what a printer can print, so a new printer type means changes in six places.

Three: the strategy menu only changed a String, and the spools lived in three places that had to be kept in sync by hand. That caused real bugs, for example a crash on a null spool.

## Slide 10: EA2: (Re)design (Components and printers) (4:45 to 5:25)

*Left: the components. Middle: the Printer hierarchy.*

Now every component has one job: the console UI, the data loader, the printer manager, the strategies and the domain model. That solves problem one.

For problem two, the printers now know what they can print. canPrint is written once in Printer as a template method. Each subclass only answers: does the print fit, is the filament supported, and how many colours. PrinterFactory is the only place with the type numbers. I kept the subclasses instead of one class with flags, because a new rule fits better in a subclass.

## Slide 11: EA2: (Re)design (Scheduling and strategy) (5:25 to 5:55)

*Point at the PrintStrategy interface.*

For problem three I used the Strategy pattern, with one decision only: which free spool to use. My first idea was a strategy that picks the whole next task. When I wrote out both strategies, they needed the same two passes, so that code would be copied into every strategy.

Now PrinterManager only knows the PrintStrategy interface. I also did not make PrinterManager a Singleton, because the tests would then share global state.

## Slide 12: EA2: Refactored code (5:55 to 6:30)

*Follow the two comments in the code: pass 1, then pass 2.*

Three code changes stand out. First, selectPrintTask went from 110 to 23 lines. Pass one keeps the loaded spools, pass two changes spools through the strategy. There is no instanceof any more. Splitting the method also showed a bug: a busy printer could lose its task. That check is now the first if.

Second, canPrint in Printer: the steps are fixed and the subclasses fill in their part. Third, the PrintStrategy interface with one method, chooseSpool. All nine unit tests pass.

## Slide 13: Takeaways (6:30 to 6:50)

To wrap up, three things I learned. Design from the interface outward. Use a pattern only where the behaviour really differs. And use testability as a check of the design.

Thank you. I am happy to answer questions or to show the code.

## If you run long

Each cut saves about 5 to 8 seconds and loses nothing the slide does not already show:

1. Slide 3: drop "Apply action always includes validate action."
2. Slide 10: drop the last sentence about the subclasses. Keep it as an answer for questions.
3. Slide 11: drop the sentence about the Singleton. Keep it as an answer for questions.

## Likely questions

**Why is the Battle Engine a library and not a separate service?**
A battle belongs to one game instance and lives only in memory. A separate service adds a network call for every action and gains nothing.

**Why no Singleton for BattleEngineFacade or PrinterManager?**
Battle Engine: up to 8 players on one instance can fight at the same time, so every battle needs its own state. PrinterManager: a Singleton is global state, so the tests would influence each other.

**What happens to a battle when the instance crashes?**
The battle is lost. That is an accepted trade-off: only the results (experience and levels) must be kept, and the Battle Coordinator saves them through the Persistence Service.

**Why did the model methods stay public?**
The effects, TurnManager and ActionResolver are in other packages. Outside the engine nothing can change a battle, because the facade only hands out snapshots.

**Did the refactoring change behaviour?**
Only on purpose: 8 bugs are fixed (for example the null spool crash and the busy printer that lost its task), and Less Spool Changes now takes the longest matching spool.

**Why not split PrinterManager into repositories and a scheduler?**
In a program under 1000 lines most of those classes would only pass calls on (Middle Man). After moving the printer rules and the spool choice out, PrinterManager is about one thing.
