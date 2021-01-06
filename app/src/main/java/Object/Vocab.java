package Object;

public class Vocab {
    private String _name;
    private String _meaning;
    private String _sentence;
    private String _pronoun;
    public Vocab(String name,String meaning, String sentence, String pronoun){
        _name = name;
        _meaning = meaning;
        _sentence = sentence;
        _pronoun = pronoun;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_meaning() {
        return _meaning;
    }

    public void set_meaning(String _meaning) {
        this._meaning = _meaning;
    }

    public String get_sentence() {
        return _sentence;
    }

    public void set_sentence(String _sentence) {
        this._sentence = _sentence;
    }

    public String get_pronoun() {
        return _pronoun;
    }

    public void set_pronoun(String _pronoun) {
        this._pronoun = _pronoun;
    }
}
