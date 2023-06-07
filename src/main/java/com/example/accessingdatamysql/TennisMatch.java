package com.example.accessingdatamysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TennisMatch {
	
	private	List<TennisSet> tennisSetList=new ArrayList<TennisSet>();
	
	public TennisMatch() {
		tennisSetList.add( new TennisSet(tennisSetList.size()+1,"A"));
	}
	
	public void winPoint(String player) {
		boolean setResult=tennisSetList.get(tennisSetList.size()-1).winPoint(player);
		
		if (setResult) {
			String currServer=tennisSetList.get(tennisSetList.size()-1).scoreInLeft();
			String nextServer="A";
			if (nextServer.equals(currServer)) {
				nextServer="B";
			}
			tennisSetList.add( new TennisSet(tennisSetList.size()+1,nextServer));
		}
	}
	
	public String displayFullResult() {
		
		String result="";
		
		TennisSet currentSet=tennisSetList.get(tennisSetList.size()-1);
		String scoreInLeft=currentSet.scoreInLeft();
		
		for ( TennisSet s:tennisSetList) {
			result+=s.displaySetScore(scoreInLeft);
		}
		
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TennisMatch [tennisSetList=");
		builder.append(tennisSetList);
		builder.append("]");
		return builder.toString();
	}

	class TennisSet{
		private int numOfSet;
		String firstServer;
		private int playerAScore;
		private int playerBScore;
		
		private boolean endGame=false;
		private List<TennisRound> roundList=new ArrayList<TennisRound>();

		public TennisSet(int numOfSet,String firstServer){
			this.numOfSet=numOfSet;
			this.firstServer=firstServer;
			roundList.add(new TennisRound(roundList.size()+1,firstServer));
		}
		
		public boolean winPoint(String player) {
			
			String roundResult=roundList.get( roundList.size()-1).winPoint(player);
			
			if (roundResult!=null&& roundResult.equals("A")) {
				playerAScore++;
			}else if (roundResult!=null&& roundResult.equals("B")) {
				playerBScore++;
			}else {
				//Not round end either set end
				return false;
			}
			
			boolean setResult=checkHasSetResult();
			
			if (setResult) {
				endGame=true;
			}else if (roundResult!=null){
				//Not set end, go to next round
				roundList.add(new TennisRound(roundList.size()+1,firstServer));				
			}
			
			return setResult;
		}
		
		private boolean checkHasSetResult() {
			
			if ( playerAScore >=6 && playerBScore <= 4) {
				return true;
			}else if ( playerBScore >=6 && playerAScore <= 4) {
				return true;
			}else if ( playerAScore >=7 && playerBScore <= (playerAScore-2)) {
				return true;
			}else if ( playerBScore >=7 && playerAScore <= (playerBScore-2)) {
				return true;
			}
			
			return false;
		}
		
		public String scoreInLeft() {
			return roundList.get( roundList.size()-1).scoreInLeft();
		}
		
		public String displaySetScore(String scoreInLeft) {
			String result="";
			
			if ( scoreInLeft.equals("A")) {
				result=(numOfSet>1?" ":"")+playerAScore+"-"+playerBScore;
			}else {
				result=(numOfSet>1?" ":"")+playerBScore+"-"+playerAScore;		
			}

			if ( !this.endGame) {
				result+=roundList.get( roundList.size()-1).displayRoundScore(scoreInLeft);				
			}
			return result;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TennisSet [numOfSet=");
			builder.append(numOfSet);
			builder.append(", firstServer=");
			builder.append(firstServer);
			builder.append(", playerAScore=");
			builder.append(playerAScore);
			builder.append(", playerBScore=");
			builder.append(playerBScore);
			builder.append(", endGame=");
			builder.append(endGame);
			builder.append(", roundList=");
			builder.append(roundList);
			builder.append("]");
			return builder.toString();
		}

		
		
	}
	
	class TennisRound{
				
		private int numOfRoundInSet;
		private String firstServerInSet;
		private int playerAPoint;
		private int playerBPoint;
		String playerWinInRound;
		
		public TennisRound(int numOfRoundInSet, String firstServerInSet) {
			this.numOfRoundInSet=numOfRoundInSet;
			this.firstServerInSet=firstServerInSet;
		}
		
		public String winPoint(String player) {
			
			if ( player==null) {
				return null;
			}
			
			if ( playerWinInRound!=null) {
				return null;
			}
			
			if ( player.equals("A")) {
				playerAPoint++;				
			}else if ( player.equals("B")) {
				playerBPoint++;
			}
			
			return checkResult();
		}
		
		private String checkResult() {
			if ( checkPointToWin( playerAPoint, playerBPoint) ) {			
				playerWinInRound="A";
				return "A";
			}else if ( checkPointToWin( playerBPoint, playerAPoint)) {
				playerWinInRound="B";
				return "B";				
			}
			return null;
		}
		
		private boolean checkPointToWin(int point1,int point2) {
			if ( point1==4 && point2<=2) {
				return true;
			}else if ( point1>=4 &&point2<=(point1-2)) {
				return true;
			}
			return false;
		}
		
		public String scoreInLeft() {
			if ( firstServerInSet.equals("A")) {
				if ( this.numOfRoundInSet%2==1) {
					return "A";
				}else {
					return "B";
				}
			}else {
				if ( this.numOfRoundInSet%2==1) {
					return "B";
				}else {
					return "A";
				}
			}
		}
		
		public String displayRoundScore(String scoreInLeft) {
			boolean advanScoreA=false;
			boolean advanScoreB=false;
			
			if (playerAPoint==0 && playerBPoint==0) {
				return "";
			}
			
			if (playerAPoint>=4&&playerBPoint==(playerAPoint-1)) {
				advanScoreA=true;
			}else if (playerBPoint>=4&&playerAPoint==(playerBPoint-1)) {
				advanScoreB=true;
			}
			
			String displayScoreA=advanScoreA?"A":String.valueOf(convertPointToScore(playerAPoint));
			String displayScoreB=advanScoreB?"A":String.valueOf(convertPointToScore(playerBPoint));
			
			if ( scoreInLeft.equals("A")) {
				return " "+displayScoreA+"-"+displayScoreB;	
			}else {
				return " "+displayScoreB+"-"+displayScoreA;
			}
			
		}
		
		private int convertPointToScore(int point) {
			switch(point) {
			case 0:
				return 0;
			case 1:
				return 15;
			case 2:
				return 30;
			}	
			return 40;

		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TennisRound [numOfRoundInSet=");
			builder.append(numOfRoundInSet);
			builder.append(", firstServerInSet=");
			builder.append(firstServerInSet);
			builder.append(", playerAPoint=");
			builder.append(playerAPoint);
			builder.append(", playerBPoint=");
			builder.append(playerBPoint);
			builder.append(", playerWinInRound=");
			builder.append(playerWinInRound);
			builder.append("]");
			return builder.toString();
		}
		
	}
	
	public static String parseLineToResult( String line) {
		TennisMatch match=new TennisMatch();
		if (line!=null) {
			for(int i=0; i<=line.length()-1; i++) {
				match.winPoint(String.valueOf( line.charAt(i)));
			}
		}
		return match.displayFullResult();
//		System.out.print("\n");
		
//		System.out.print(match+"\n");
	}
	
	public static void main( String[] argu) {
		
//		final String INPUT_FILE_PATH=argu[0];//"/Users/ray/Downloads/ForCandidates/input.txt";
//		final String OUTPUT_FILE_PATH=argu[1];//"/Users/ray/Downloads/ForCandidates/output.txt";
		final String INPUT_FILE_PATH="/Users/ray/Downloads/ForCandidates/input.txt";
		final String OUTPUT_FILE_PATH="/Users/ray/Downloads/ForCandidates/output.txt";
		
		BufferedReader reader;
		BufferedWriter writer;

		try {
			reader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
			writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
			String line = reader.readLine();
			writer.write( parseLineToResult(line));
			writer.newLine();

			while (line != null) {
				// read next line
				line = reader.readLine();
				
				if ( line !=null) {
					writer.write( parseLineToResult(line));
					writer.newLine();
				}
			}

			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}