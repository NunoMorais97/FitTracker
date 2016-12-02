/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */

import FitnessTracker.*;
import FitnessTracker.Exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;

import java.io.*;
import java.util.Scanner;


public class Main {




    //constants for sucess strings
    private static final String FILE = "backup_file.txt";
    private static final String ATLETA_ATUALIZADO_COM_SUCESSO = "Atleta atualizado com sucesso.";
    private static final String ATLETA_REMOVIDO_COM_SUCESSO = "Atleta removido com sucesso.";
    private static final String INSERCAO_DE_ATLETA_COM_SUCESSO = "Insercao de atleta com sucesso.";
    private static final String _PS = " ps";
    private static final String _CAL_ = " cal, ";
    private static final String FORMAT_2 = ": ";
    private static final String FORMAT_CONSULT1 = FORMAT_2;
    private static final String GRUPO_ = "Grupo ";
    private static final String _PS_1 = " ps (";
    private static final String _ANOS_ = " anos, ";
    private static final String KILOS = " kg, ";
    private static final String GRAVANDO_E_TERMINANDO = "Gravando e terminando...";
    private static final String __MASCULINO_ = ": Masculino, ";
    private static final String __FEMININO_ = ": Feminino, ";
    private static final String GRUPO_CRIADO_COM_SUCESSO = "Grupo criado com sucesso.";
    private static final String ADESAO_REALIZADA_COM_SUCESSO = "Adesao realizada com sucesso.";
    private static final String DESISTENCIA_REALIZADA_COM_SUCESSO = "Desistencia realizada com sucesso.";
    private static final String ATUALIZACAO_DE_PASSOS_COM_SUCESSO = "Atualizacao de passos com sucesso.";
    private static final String SPACE = " ";
    private static final String _CAL = " cal";
    private static final String TREINO_ADICIONADO_COM_SUCESSO = "Treino adicionado com sucesso.";
    private static final String ATIVIDADE_CRIADA_COM_SUCESSO = "Atividade criada com sucesso.";


    //Constants for exceptions
    private static final String ERROR_ALREADY_EXISTING_ACTIVITY = "Atividade existente.";
    private static final String ERROR_NON_EXISTING_ACTIVITY = "Atividade inexistente.";
    private static final String ERROR_INVALID_MET = "MET invalido.";
    private static final String ATHLETE_NON_EXISTING = "Atleta inexistente.";
    private static final String ATHLETE_EXISTING = "Atleta existente.";
    private static final String NOT_EXISTING_IN_GROUP_ATHLETE = "Atleta nao pertence ao grupo.";
    private static final String ERROR_NON_EXISTING_GROUP = "Grupo inexistente.";
    private static final String ERROR_ALREADY_EXISTING_GROUP = "Grupo existente.";
    private static final String ERROR_NO_GROUPS_EXISTING = "Nao existem grupos.";
    private static final String INVALID_VALUES_ERROR = "Valores invalidos.";
    private static final String INVALID_TIME_WORKOUT ="Tempo invalido.";
    private static final String INVALID_STEPS = "Numero de passos invalido.";
    private static final String EMPTY_GROUP = "Grupo nao tem adesoes.";
    private static final String ALREADY_HAS_GROUP = "Atleta ja tem grupo.";
    private static final String INVALID_TYPE = "Opcao invalida.";
    private static final String HAS_NO_WORKOUTS = "Atleta sem treinos.";

    private static void insertAthlete(Scanner in, Tracker tracker) {

        try {
            String id= in.next().toLowerCase();
            int weight =in.nextInt();
            int height = in.nextInt();
            int age = in.nextInt();
            char sex =in.next().charAt(0);
            sex = Character.toUpperCase(sex);
            String name = in.next();
            name = name + in.nextLine();

            // because of in.nextLine() names came with spaces in the end,
            // this removes the last char (space) of the name , if it exists
            if(name.endsWith(" "))
                if (name.length() > 0 && name.charAt(name.length()-1)==' ') {
                    name = name.substring(0, name.length()-1);
                }

            tracker.insertAthlete(id, weight, height, age, sex, name);
            System.out.println(INSERCAO_DE_ATLETA_COM_SUCESSO);

        } catch (AlreadyExistingAthleteException e) {
            System.out.println(ATHLETE_EXISTING);

        } catch (InvalidValuesException e) {
            System.out.println(INVALID_VALUES_ERROR);
        }
    }

    private static void updateAthlete(Scanner in, Tracker tracker) {
        try {
            String  id = in.next().toLowerCase();
            int weight = in.nextInt();
            int height = in.nextInt();
            int age = in.nextInt();

            tracker.changeAthleteInfo(id, weight, height, age);
            System.out.println(ATLETA_ATUALIZADO_COM_SUCESSO);
        } catch (InvalidValuesException e) {
            System.out.println(INVALID_VALUES_ERROR);
        } catch (NonExistingAthleteException e) {
            System.out.println(ATHLETE_NON_EXISTING);
        }

    }


    private static void removeAthlete(Scanner in, Tracker tracker) {
        String id = in.next().toLowerCase();
        in.nextLine();

        try {
            tracker.removeAthlete(id);
            System.out.println(ATLETA_REMOVIDO_COM_SUCESSO);
        } catch (NonExistingAthleteException e) {
            System.out.println(ATHLETE_NON_EXISTING);
        }
    }

    private static void consultAthlete(Scanner in, Tracker tracker) {
        String id = in.next().toLowerCase();
        in.nextLine();
        try {
            Athlete athlete = tracker.consultAthlete(id);
            listAthleteStats(athlete);
        } catch (NonExistingAthleteException e) {
            System.out.println(ATHLETE_NON_EXISTING);
        }
    }


    private static void createActivity(Scanner in, Tracker tracker) {
        String id = in.next().toLowerCase();
        int MET=in.nextInt();
        String name = in.next();
        name = name + in.nextLine();

        if(name.endsWith(" "))
            if (name.length() > 0 && name.charAt(name.length()-1)==' ') {
                name = name.substring(0, name.length()-1);
            }

        try {
            tracker.createActivity(id, MET, name);
            System.out.println(ATIVIDADE_CRIADA_COM_SUCESSO);
        } catch (ActivityAlreadyExistingException e) {
            System.out.println(ERROR_ALREADY_EXISTING_ACTIVITY);
        } catch (InvalidMETException e){
            System.out.println(ERROR_INVALID_MET);

        }
    }


    private static void addWorkout(Scanner in, Tracker tracker) {
        try {
            String athlete_id = in.next().toLowerCase();
            String activity_id = in.next().toLowerCase();
            int duration = in.nextInt();
            in.nextLine();


            tracker.addWorkout(athlete_id, activity_id, duration);
            System.out.println(TREINO_ADICIONADO_COM_SUCESSO);
        } catch ( NonExistingAthleteException e){
            System.out.println(ATHLETE_NON_EXISTING);
        }catch (NonExistingActivityException e){
            System.out.println(ERROR_NON_EXISTING_ACTIVITY);
        } catch (InvalidWorkoutTimeException e) {
            System.out.println(INVALID_TIME_WORKOUT);
        }
    }


    private static void consultAthleteWorkouts(Scanner in, Tracker tracker) {
        String athleteID = in.next().toLowerCase();
        char type = in.next().charAt(0);
        type = Character.toUpperCase(type); //  ||
        in.nextLine();
        Workout workout ;

        try {

            Iterator<Workout> iterator = tracker.ListWorkouts(athleteID,type);
            while (iterator.hasNext()) {
                workout = iterator.next();
                System.out.println(workout.getActivity().getName() + SPACE + workout.getCalories() + _CAL);
            }


        } catch (NonExistingAthleteException e) {
            System.out.println(ATHLETE_NON_EXISTING);
        }
        catch(InvalidTypeException e ){
            System.out.println(INVALID_TYPE);
        }
        catch (HasNoWorkoutsException e) {
            System.out.println(HAS_NO_WORKOUTS);
        }
        catch(NoSuchElementException e){
            //Do nothing
        }
    }


    private static void updateSteps(Scanner in, Tracker tracker) {
        try {
            String id = in.next().toLowerCase();
            int steps =  in.nextInt();

            tracker.addSteps(id, steps);
            System.out.println(ATUALIZACAO_DE_PASSOS_COM_SUCESSO);
        } catch (InvalidStepsException  ex) {
            System.out.println(INVALID_STEPS);
        } catch (NonExistingAthleteException e) {
            System.out.println(ATHLETE_NON_EXISTING);
        }

    }


    private static void insertGroup(Scanner in, Tracker tracker) {
        String groupID = in.next().toLowerCase();
        String name =in.next();

        try {
            tracker.createGroup(groupID, name);
            System.out.println(GRUPO_CRIADO_COM_SUCESSO);
        } catch (GroupAlreadyExistsException e) {
            System.out.println(ERROR_ALREADY_EXISTING_GROUP);
        }

    }


    private static void joinGroup(Scanner in, Tracker tracker) {
        String athleteID= in.next().toLowerCase();
        String groupID =in.next().toLowerCase();

        try {
            tracker.joinGroup(athleteID, groupID);
            System.out.println(ADESAO_REALIZADA_COM_SUCESSO);
        } catch ( AlreadyHasGroupException  e) {
            System.out.println(ALREADY_HAS_GROUP);
        } catch(GroupNonExistingException e) {
            System.out.println(ERROR_NON_EXISTING_GROUP);
        } catch(NonExistingAthleteException e){
            System.out.println(ATHLETE_NON_EXISTING);
        }
    }



    private static void leaveGroup(Scanner in, Tracker tracker) {
        String athleteID = in.next().toLowerCase();
        String groupID = in.next().toLowerCase();

        try {
            tracker.leaveGroup(athleteID, groupID);
            System.out.println(DESISTENCIA_REALIZADA_COM_SUCESSO);

        } catch (AthleteNotInGroupException e) {
            System.out.println(NOT_EXISTING_IN_GROUP_ATHLETE);
        } catch (GroupNonExistingException e) {
            System.out.println(ERROR_NON_EXISTING_GROUP);
        } catch (NonExistingAthleteException e) {
            System.out.println(ATHLETE_NON_EXISTING);
        }
    }


    private static void consultGroup(Scanner in, Tracker tracker) {
        String groupID= in.next();

        try {
            Group group = tracker.consultGroup(groupID);
            System.out.println(GRUPO_ + group.getName() + FORMAT_CONSULT1 + group.getCalories() + _CAL_ + group.getSteps() + _PS );
        } catch (GroupNonExistingException e) {
            System.out.println(ERROR_NON_EXISTING_GROUP);
        }

    }


    private static void listGroup(Scanner in, Tracker tracker) {
        String groupID= in.next().toLowerCase();
        try {
            Iterator<Entry<String,Athlete>> iterator = tracker.listGroup(groupID);
            while(iterator.hasNext()) {
                Athlete athlete = iterator.next().getValue();

                if( athlete.getSex() == 'F')
                    System.out.println(athlete.getName() + __FEMININO_ + athlete.getWeight()+ KILOS +athlete.getAge()
                            + _ANOS_ + athlete.getCalories() + _CAL_ + athlete.getSteps() + _PS );
                else if(athlete.getSex() == 'M')
                    System.out.println(athlete.getName() + __MASCULINO_ + athlete.getWeight()+ KILOS +athlete.getAge()
                            +  _ANOS_ + athlete.getCalories() + _CAL_ + athlete.getSteps()+ _PS);
            }
        } catch (EmptyGroupException e) {
            System.out.println(EMPTY_GROUP);
        } catch (GroupNonExistingException e) {
            System.out.println(ERROR_NON_EXISTING_GROUP);
        }
    }

    private static void listAthleteStats(Athlete athlete){

        if( athlete.getSex() == 'F')
            if(athlete.getGroup()!= null)
                System.out.println(athlete.getName() + __FEMININO_ + athlete.getWeight()+ KILOS +athlete.getAge()
                        + _ANOS_ + athlete.getCalories() + _CAL_ + athlete.getSteps() + _PS_1 + athlete.getGroup().getName() + ")");

            else  System.out.println(athlete.getName() + __FEMININO_ + athlete.getWeight()+ KILOS +athlete.getAge()
                    + _ANOS_ + athlete.getCalories() + _CAL_ + athlete.getSteps() + _PS );

        else if(athlete.getSex() == 'M')

            if(athlete.getGroup()!= null)
                System.out.println(athlete.getName() + __MASCULINO_ + athlete.getWeight()+ KILOS +athlete.getAge()
                        + _ANOS_ + athlete.getCalories() + _CAL_ + athlete.getSteps() + _PS_1 + athlete.getGroup().getName() + ")");

            else System.out.println(athlete.getName() + __MASCULINO_ + athlete.getWeight()+ KILOS +athlete.getAge()
                    +  _ANOS_ + athlete.getCalories() + _CAL_ + athlete.getSteps()+ _PS);
    }




    private static void listWalkers( Tracker tracker) {

        try {
            Iterator<Group> iterator = tracker.ListGroupsBySteps();
            while(iterator.hasNext()) {
                Group group = iterator.next();
                listGroupStats(group);
            }

        } catch (NoGroupsExistingException e) {
            System.out.println(ERROR_NO_GROUPS_EXISTING);
        }


    }

    private static void listGroupStats(Group group){
        System.out.println(GRUPO_ + group.getName() + FORMAT_2 + group.getCalories() + _CAL_ + group.getSteps() + _PS);
    }

    private static void ListWarriors(Tracker tracker) {

        try {
            Iterator<Group> iterator = tracker.ListGroupsByCalories();
            while(iterator.hasNext()) {
                Group group = iterator.next();
                listGroupStats(group);
            }
        } catch (NoGroupsExistingException  e) {
            System.out.println(ERROR_NO_GROUPS_EXISTING);
        } catch (GroupNonExistingException e){
            System.out.println(ERROR_NON_EXISTING_GROUP);
        } catch(EmptyGroupException e){
            System.out.println(EMPTY_GROUP);
        }
    }

    private static void saveAndExit(String filename,Tracker tracker) {

        try{
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filename));
            file.writeObject(tracker);
            file.flush();
            file.close();
        }
        catch(IOException e){
        }
        System.out.println(GRAVANDO_E_TERMINANDO);
    }

    private static Tracker load(String filename) {
        Tracker tracker = null;
        try{
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename));
            tracker = (Tracker) file.readObject();
            file.close();
        } catch(FileNotFoundException e) {
            tracker = new TrackerClass();
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        return tracker;
    }





    // command strings
    private enum Commands{
        IU ,UU ,RU ,CU, IA, AW, CW,
        AS ,IG ,AG, CG, LG, DG, LC, LW,
        XS
    }


    private static Commands getCommand(Scanner input) {
        return Commands.valueOf(input.next().toUpperCase());
    }

    public static void main(String[] args)  {


        Scanner in = new Scanner(System.in);
        Commands op = getCommand(in);
        Tracker tracker = load(FILE);


        while(!op.equals(Commands.XS)){


            switch (op){

                case IU : insertAthlete(in, tracker); break;

                case UU : updateAthlete(in, tracker); break;

                case RU : removeAthlete(in, tracker); break;

                case CU : consultAthlete(in, tracker); break;

                case IA : createActivity(in, tracker); break;

                case AW : addWorkout(in, tracker); break;

                case CW : consultAthleteWorkouts(in, tracker); break;

                case AS : updateSteps(in, tracker); break;

                case IG : insertGroup(in, tracker); break;

                case AG : joinGroup(in, tracker); break;

                case DG : leaveGroup(in, tracker); break;

                case CG : consultGroup(in, tracker); break;

                case LG : listGroup(in,tracker); break;

                case LC : listWalkers(tracker); break;

                case LW : ListWarriors(tracker); break;

                default: 	break;

            }
            System.out.println();

            op = getCommand(in);


        }
        saveAndExit(FILE, tracker);
        System.out.println("");
    }
}
