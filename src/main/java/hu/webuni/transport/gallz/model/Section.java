package hu.webuni.transport.gallz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;

@NamedEntityGraph(
		name = "Section-entity-graph", 
		attributeNodes = { 
			@NamedAttributeNode("fromMilestone"),
			@NamedAttributeNode("toMilestone"),
			@NamedAttributeNode("transportplan"), 
		}
)

@NamedEntityGraph(
	name = "Section-entity-graph-with-address", 
	attributeNodes = { 
		@NamedAttributeNode(value = "fromMilestone", subgraph = "milestone-subgraph"),
		@NamedAttributeNode(value = "toMilestone", subgraph = "milestone-subgraph"),
		@NamedAttributeNode("transportplan"), 
	},
	subgraphs = {
		@NamedSubgraph(
			name = "milestone-subgraph",
			attributeNodes = {
				@NamedAttributeNode("address")
			}
		)
	}
)
@Entity
public class Section {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne
	@JoinColumn
	private Milestone fromMilestone;
	
	@ManyToOne
	@JoinColumn
	private Milestone toMilestone;
	
	@ManyToOne
	@JoinColumn
	private TransportPlan transportplan;
	
	private Integer sectNumber;
	
	public Section() {}	

	public Section(Integer sectNumber, Milestone fromMilestone, Milestone toMilestone) {
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.sectNumber = sectNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}

	public Integer getSectNumber() {
		return sectNumber;
	}

	public void setSectNumber(Integer sectNumber) {
		this.sectNumber = sectNumber;
	}

	public TransportPlan getTransportplan() {
		return transportplan;
	}

	public void setTransportplan(TransportPlan transportplan) {
		this.transportplan = transportplan;
	}
	
}
