/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;


public class AddressBook {
    private Person[] myAddressBook;
    private final int size = 5;

    public AddressBook() {
        myAddressBook = new Person[size];
        fillAddressBook();
    }

    private void fillAddressBook() {
        String[] names = {"Juan", "Jose", "Luis", "Agustin", "Jesus"};
        for (int i = 0; i < size; i++) {
            myAddressBook[i] = new Person(i, names[i]);
        }
    }

    public Person getRecord(int key) {
        if (key < size)
            return myAddressBook[key];
        else {
            return new Person();
        }
    }


} 