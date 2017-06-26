
#
#   parse a StackOverflow posts file and print out answers in a TSV format,
#   one answer per line.
#   Output fields:
#       post id
#       owner
#

import sys
import xml.sax

answerId = "2"

class StackContentHandler(xml.sax.ContentHandler):

    def __init__(self):
        xml.sax.ContentHandler.__init__(self)
 
    def startElement(self, name, attrs):
        '''
        finds posts that are answers, then prints id and owner
        '''
        
        # only 'row' elements are relevant, skip elements that are not rows
        if name != "row":
            return

        # get post type
        ptype = "__none__"
        if attrs.has_key("PostTypeId"):
            ptype = attrs.getValue("PostTypeId")

        # only answers are relevant, skip elements with PostTypeId != answerId
        if ptype != answerId:
            return

        # extract post id and owner
        id = "__none__"
        if attrs.has_key("Id"):
            id = attrs.getValue("Id")
        owner = "__none__"
        if attrs.has_key("OwnerUserId"):
            owner = attrs.getValue("OwnerUserId")
        parent = "__none__"
        if attrs.has_key("ParentId"):
            parent = attrs.getValue("ParentId")
        date = "__none__"
        if attrs.has_key("CreationDate"):
            date = attrs.getValue("CreationDate")
        score = "__none__"
        if attrs.has_key("Score"):
            score = attrs.getValue("Score")
        last_activity = "__none__"
        if attrs.has_key("LastActivityDate"):
            last_activity = attrs.getValue("LastActivityDate")
        comment_count = "__none__"
        if attrs.has_key("CommentCount"):
            comment_count = attrs.getValue("CommentCount")        
    
        line = []
        line.append(id)
        line.append(owner)
        line.append(parent)
        line.append(date)
        line.append(score)
        line.append(last_activity)
        line.append(comment_count)
        print "\t".join(line)
 
if __name__ == '__main__':

    if len(sys.argv) < 2:
        print "Usage: " + sys.argv[0] + " <file>"
        sys.exit(1)

    fname = sys.argv[1]
    #print fname

    f = open(fname)
    xml.sax.parse(f, StackContentHandler())

