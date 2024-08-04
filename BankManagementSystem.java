/*
1.
Assume that a bank maintains two kinds of accounts for customers,
one called as savings account and the other as current account:
the savings account provides compound interest and withdrawal facilities
but not cheque book facility. 
the current provides cheque book facility but no interest. 
Current account holders should also maintain a minimum balance and if the balance 
falls below this level, a service charge is imposed.
create a structure account that stores customer name, account number and opening balance.
from this derive the structures current and savings to make them more specific to their requirements.
now do the following tasks:
a) deposit an amount for a customer and update the balance.
b) display the account details.
c) compute and deposit interest.
d) withdraw amount for a customer after checking the balance and update the
balance.
e) check for the minimum balance(for current account holders), impose penalty,
if necessary, and update the balance.
*/

import java.util.*;

enum Type{ SAVINGS, CURRENT }

class BankManagementSystem{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Bank bank1 = new Bank("Glasses Shatter Bank","Bank Address","Bank Branch","Prem Kumar");
		System.out.println("Enter name:");
		String name = sc.nextLine();
		System.out.println("Enter account number:");
		int accNo = sc.nextInt();
		
		for(AccHolder accHolder: bank1.getAccHolder())
			if(accHolder.name.equals(name)){
				if(accNo == accHolder.account.accNumber){
					System.out.println("--------------------------");
					System.out.println("1.Check Balance\n2.Withdraw Amount\n3.Deposit Amount\n4.Compute Interest\n5.Account Details\n6.Exit");
					int choice = sc.nextInt();
					int amount;
					while(choice != 6 && choice < 6 && choice > 0){
						switch(choice){
							case(1):
								bank1.checkBalance(accHolder.account);
								break;
							case(2):
								System.out.println("Enter amount:");
								amount = sc.nextInt();
								bank1.withdrawal(accHolder.account,amount);
								break;
							case(3):
								System.out.println("Enter amount:");
								amount = sc.nextInt();
								bank1.deposit(accHolder.account,amount);
								break;
							case(4):
								System.out.println("Enter time period:");
								int timePeriod = sc.nextInt();
								bank1.computeInterest(accHolder.account,timePeriod);
								System.out.println("Account balance after depositing interest.");
								bank1.checkBalance(accHolder.account);
								break;
							case(5):
								bank1.display(accHolder.account,accHolder,bank1);
								break;
						}
						System.out.println("--------------------------");
						System.out.println("1.Check Balance\n2.Withdraw Amount\n3.Deposit Amount\n4.Compute Interest\n5.Account Details\n6.Exit");
						choice = sc.nextInt();
					}break;
				}else{
					System.out.println("Account number doesnt not match.");
					break;
				}
			}else{
				System.out.println("There is no account under this name.");
				break;
			}
	}
}

class Accounts{
	int accNumber;
	double balance;
	Type accType;
	Accounts(int accNumber,double balance,Type accType){
		this.accNumber = accNumber;
		this.balance = balance;
		this.accType = accType;
	}
	
	public void accDetails(){ }
	class Savings{
		double compInterest = 10;
		
		public void withdrawal(int amount){ }	
	}

	class Current{
		void CheckBook(){ }
	}
	Savings savings = new Savings();
	Current current = new Current();
}


class AccHolder{
	Accounts account;
	String name;
	int age;
	String phoneNumber;
	String address;
	String aadharNumber;
	String panNumber;
	AccHolder(Accounts account,String name,int age,String phoneNumber,String address,String aadharNumber,String panNumber){
		this.account = account;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
	}
	
	void changeAddress(String newAddress){ this.address = newAddress; }

	void changePhoneNo(String newPhoneNo){ this.phoneNumber = newPhoneNo; }
	
	void changeName(String newName){ this.name = newName; }

}


class Bank extends BankUtil{
	String name;
	String address;
	String branch;
	String managerName;
	Bank(String name,String address,String branch,String managerName){
		this.name = name;
		this.address = address;
		this.branch = branch;
		this.managerName = managerName;
	}
	
	Accounts account1 = new Accounts(240501,30000,Type.SAVINGS);
	Accounts account2 = new Accounts(240502,35000,Type.CURRENT);
	Accounts account3 = new Accounts(240503,50000,Type.SAVINGS);
	
	ArrayList<Accounts> accounts = new ArrayList<Accounts>();
	{
	accounts.add(account1);
	accounts.add(account2);
	accounts.add(account3);
	}
	
	public ArrayList<Accounts> getAccounts(){	
		return accounts;
	}

	AccHolder accHolder1 = new AccHolder(account1,"Raghu",22,"7345699811","23/3,\nR Colony,\nChennai","624286652002","BPH4545HI");
	AccHolder accHolder2 = new AccHolder(account2,"Sakthi",23,"7345645533","Address 2","624276652002","OCM7575DF");
	AccHolder accHolder3 = new AccHolder(account3,"Siva",21,"7345654321","Address 3","624296652002","CCG5565CS");
	
	ArrayList<AccHolder> accHolders = new ArrayList<AccHolder>();
	{
	accHolders.add(accHolder1);
	accHolders.add(accHolder2);
	accHolders.add(accHolder3); 
	}

	public ArrayList<AccHolder> getAccHolder(){	
		return accHolders;
	}
	//void createAcc(){ }


}

class BankUtil{
	final double MINBALANCE = 5000;
	public void display(Accounts account,AccHolder accHolder,Bank bank){
		System.out.println("----------------Account details-------------");
		System.out.println("Bank Name: "+bank.name);
		System.out.println("Bank Branch: "+bank.branch);
		System.out.println("Account Number: "+account.accNumber);
		System.out.println("Acconut Holder Name: "+accHolder.name);
		System.out.println("Acconut Holder Age: "+accHolder.age);
		System.out.println("Acconut Holder Address: "+accHolder.address);
		System.out.println("Account Type: "+account.accType);
	}
	
	public void deposit(Accounts account,double amount){
		account.balance += amount;
		System.out.println("Amount Rs."+amount+" deposited");
	}
	
	public void computeInterest(Accounts account,int timePeriod){
		if(account.accType == Type.SAVINGS)
			for(int i = 0;i < timePeriod;i++){
				double interest = (account.balance * (account.savings.compInterest/100));
				account.balance += interest;
			}
		else
			System.out.println("Interest cannot be calculated for Current Account.");
	}
	
	public void checkBalance(Accounts account){
		System.out.println("Account Balance :"+account.balance);
	}
	
	public void withdrawal(Accounts account,double amount){
		if(account.balance >= amount){
			System.out.println("Amount withdrawn: "+amount);
			account.balance -= amount;
			minimumBalance(account);
		}
		else
			System.out.println("Insufficient Balance.");
	}
	
	public void minimumBalance(Accounts account){
		if(account.balance < MINBALANCE){
			System.out.println("Your account balance falls behind Minimum Balance.\n Penality will be incurred.");
			account.balance -= MINBALANCE*0.05;
		}
	}

}