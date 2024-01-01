package bibledata;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import creek.*;

public class ParseTaggedJSON {

	Table words;

	public ParseTaggedJSON ( String path, Table table ) throws Exception {
		String[] lines = FileActions.read( path )
			.split( "(:\\s{0,1}\\{|\\},)" );
		words = Tables.regexGroups( Arrays.asList(lines), "([\\w\\.,!|\"]+)", table );
		List<String> verse = new ArrayList<>();
		for (int i=0; i<words.rowCount(); i++) {
			if (Tables.regexFound("\\w{3}\\|")) {
				verse = new ArrayList<>();
				verse.add( words.item(i,0) );
			}
			if (Tables.regexFound("(G|H)\\d{3}")) {
				verse.add( words.item(i,0) );
			}
		}
	}

	public Table words () {
		return words;
	}
	
	public Table verses () {
		
	}

	public static void main ( String[] args ) throws Exception {
		ParseTaggedJSON ptj = new ParseTaggedJSON( args[0], new SimpleTable() );
		System.out.println( ptj.words() );
	}

}
