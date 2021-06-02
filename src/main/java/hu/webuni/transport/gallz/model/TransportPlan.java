package hu.webuni.transport.gallz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

@NamedEntityGraph(
		name = "TransportPlan-entity-graph", 
		attributeNodes = { 
			@NamedAttributeNode("income"),
			@NamedAttributeNode("sections"), 
		}
	)
@NamedEntityGraph(
		name = "TransportPlan-entity-graph-with-milestone", 
		attributeNodes = { 
				@NamedAttributeNode("income"),
				@NamedAttributeNode(value = "sections", subgraph = "sectioins-subgraph"), 
			},
		subgraphs = {
			@NamedSubgraph(
				name = "sectioins-subgraph",
				attributeNodes = {
					@NamedAttributeNode("fromMilestone")
				}
			)
		}
	)
@Entity
public class TransportPlan {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private Long income;
	
	@OneToMany(mappedBy="transportplan")
	private List<Section> sections;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public void addSection(Section section) {
		if(this.sections == null)
			this.sections = new ArrayList<>();
		this.sections.add(section);
		section.setTransportplan(this);
	}
	
}
