package Object;

import java.util.List;

public class Topic {
    private String _name;
    private List<Vocab> _vocabs;
    public Topic(String name, List<Vocab> vocabs){
        this._name = _name;
        this._vocabs = vocabs;
    }
    public String get_name(){return _name;};
    public Vocab getVocab(int i){return _vocabs.get(i);};
    public void set_name(String name){_name = name;};
    public void set_vocabs(Vocab voc){
        _vocabs.add(voc);
    }

}
