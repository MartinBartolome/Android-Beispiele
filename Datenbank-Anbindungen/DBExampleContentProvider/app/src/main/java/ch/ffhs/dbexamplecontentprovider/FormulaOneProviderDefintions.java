package ch.ffhs.dbexamplecontentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class FormulaOneProviderDefintions {
    public static final String AUTHORITY = "ch.ffhs.DBExampleContentProvider" ;

    // This class cannot be instantiated
    private FormulaOneProviderDefintions()
    {
    }

    /**
     * Defines the elements of the provider.
     */
    public static final class Entry implements BaseColumns
    {
        // This class cannot be instantiated
        private Entry()
        {
        }

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/entries") ;

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of entries.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.dbexmpl.entries" ;

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single entry.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.dbexmpl.entry" ;


        /**
         * The text of the entry
         * <P>Type: TEXT</P>
         */
        public static final String FAHRER = "Fahrer" ;
        public static final String TEAM = "Team" ;
        public static final String PUNKTE = "Punkte" ;
        public static final String SIEGE = "Siege" ;
    }
}
